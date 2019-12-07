package ru.homework.service.Impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.dto.EmployeeView;
import ru.homework.exception.CustomException;
import ru.homework.mapper.MapperFacade;
import ru.homework.model.Department;
import ru.homework.model.Employee;
import ru.homework.model.Position;
import ru.homework.repository.EmployeeRepository;
import ru.homework.service.DepartmentService;
import ru.homework.service.EmployeeService;
import ru.homework.service.PositionService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentService departmentService;

    private final PositionService positionService;

    private final MapperFacade mapperFacade;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService, PositionService positionService, MapperFacade mapperFacade) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.positionService = positionService;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeView> findEmployerViewsInDepartment(int departmentId) {
        List<Employee> employees = findEmployersInDepartment(departmentId);
        return mapperFacade.mapAsList(employees, EmployeeView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findEmployersInDepartment(int departmentId) {
        Department department = departmentService.findById(departmentId);
        return employeeRepository.findAllByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findChiefInDepartment(Department department) {
        return employeeRepository.findEmployeeByChiefAndDepartment(true, department);
    }

    @Override
    @Transactional(readOnly = true)
    public int countDepartmentEmployers(Department department) {
        return employeeRepository.countEmployeesByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeView get(int id) {
        return mapperFacade.map(findById(id), EmployeeView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeView getChief(int id) {
        Employee employee = findById(id);
        List<Employee> employees = employee.getDepartment().getEmployees();
        Employee chief = employees.stream().filter(Employee::isChief).findFirst().orElse(null);
        if (chief == null) {
            throw new CustomException("Начальник не найден");
        }
        return mapperFacade.map(chief, EmployeeView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeView> findByFields(EmployeeView employeeView) {
        List<Employee> findedEmployers = employeeRepository.findAll(findBy(employeeView));
        return mapperFacade.mapAsList(findedEmployers, EmployeeView.class);
    }

    @Override
    @Transactional
    public void create(EmployeeView employeeView) {
        Employee employee = mapperFacade.map(employeeView, Employee.class);
        Department department = departmentService.findById(employeeView.getDepartmentId());
        Position position = positionService.findById(employeeView.getPositionId());
        employee.setDepartment(department);
        checkChiefExistence(employee, department);
        employee.setPosition(position);
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public EmployeeView update(EmployeeView employeeView) {
        Employee employee = findById(employeeView.getId());
        mapperFacade.map(employeeView, employee);
        if (employee.getDepartment().getId() != employeeView.getDepartmentId()) {
            employee.setDepartment(departmentService.findById(employeeView.getDepartmentId()));
        }
        if (employee.getPosition().getId() != employeeView.getPositionId()) {
            checkChiefExistence(employee, employee.getDepartment());
            employee.setPosition(positionService.findById(employeeView.getPositionId()));
        }
        employeeRepository.saveAndFlush(employee);
        return mapperFacade.map(employee, EmployeeView.class);
    }

    @Override
    @Transactional
    public EmployeeView dismiss(int employerId, Date dismissalDay) {
        Employee employee = findById(employerId);
        if (employee.getDismissalDay() != null) {
            throw new CustomException("Сотрудник уже уволен");
        }
        employee.setDismissalDay(dismissalDay);
        employeeRepository.saveAndFlush(employee);
        return mapperFacade.map(employee, EmployeeView.class);
    }

    @Override
    @Transactional
    public EmployeeView transfer(int employerId, int departmentId) {
        Employee employee = findById(employerId);
        Department department = departmentService.findById(departmentId);
        if (department == null) {
            throw new CustomException(String.format("Департамент c id = %s не найден", departmentId));
        }
        checkChiefExistence(employee,department);
        employee.setDepartment(department);
        return mapperFacade.map(employee, EmployeeView.class);
    }

    @Override
    @Transactional
    public List<EmployeeView> transferAll(int sourceDepartmentId, int destinationDepartmentId) {
        Department sourceDepartment = departmentService.findById(sourceDepartmentId);
        if (sourceDepartment == null) {
            throw new CustomException(String.format("Департамент c id = %s не найден", sourceDepartmentId));
        }
        Department destinationDepartment = departmentService.findById(destinationDepartmentId);
        if (destinationDepartment == null) {
            throw new CustomException(String.format("Департамент c id = %s не найден", destinationDepartmentId));
        }
        List<Employee> employees = employeeRepository.findAllByDepartment(sourceDepartment);
        for (Employee employee : employees) {
            checkChiefExistence(employee, destinationDepartment);
            employee.setDepartment(destinationDepartment);
        }
        return mapperFacade.mapAsList(employees, EmployeeView.class);
    }

    private Employee findById(int id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new CustomException(String.format("Сотрудник c id = %s не найден", id));
        }
        return optional.get();
    }

    private void checkChiefExistence(Employee employee, Department department) {
        if (employee.isChief() && findChiefInDepartment(department) != null) {
            throw new CustomException("В одном департаменте не может быть 2х сотрудников с должностью Начальник");
        }
    }

    private Specification<Employee> findBy(EmployeeView employeeView) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                int positionId = employeeView.getPositionId();
                if (positionId != 0) {
                    predicates.add(cb.equal(root.get("position"), positionId));
                }

                int departmentId = employeeView.getDepartmentId();
                if (departmentId != 0) {
                    predicates.add(cb.equal(root.get("department"), departmentId));
                }

                String name = employeeView.getName();
                if (name != null && !name.isEmpty()) {
                    predicates.add(cb.equal(root.get("name"), name));
                }

                String secondName = employeeView.getSecondName();
                if (secondName != null && !secondName.isEmpty()) {
                    predicates.add(cb.equal(root.get("secondName"), secondName));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
