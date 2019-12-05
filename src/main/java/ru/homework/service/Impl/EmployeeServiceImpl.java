package ru.homework.service.Impl;

import ma.glasnost.orika.impl.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.dto.DepartmentView;
import ru.homework.dto.EmployeeView;
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
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeView> findEmployersInDepartment(int departmentId) {
        Department department = departmentService.findById(departmentId);
        List<Employee> employees = employeeRepository.findAllByDepartment(department);
        List<EmployeeView> employeeViews = mapperFacade.mapAsList(employees, EmployeeView.class);
        return employeeViews;
    }

    @Override
    @Transactional
    public void create(EmployeeView employeeView) {
        Employee employee = mapperFacade.map(employeeView, Employee.class);
        Department department = departmentService.findById(employeeView.getDepartmentId());
        Position position = positionService.findById(employeeView.getPositionId());
        employee.setDepartment(department);
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
            employee.setPosition(positionService.findById(employeeView.getPositionId()));
        }
        employeeRepository.saveAndFlush(employee);
        EmployeeView employeeView1 = mapperFacade.map(employee, EmployeeView.class);
        return employeeView1;
    }

    @Override
    @Transactional
    public EmployeeView dismiss(int employerId, Date dismissalDay) {
        Employee employee = findById(employerId);
        if (employee.getDismissalDay() != null) {
            throw new RuntimeException();
            //todo err already dismissed
        }
        employee.setDismissalDay(dismissalDay);
        employeeRepository.saveAndFlush(employee);
        return mapperFacade.map(employee, EmployeeView.class);
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
        //todo check null
        List<Employee> employees = employee.getDepartment().getEmployees();
        Employee chief = employees.stream().filter(Employee::isChief).findFirst().orElse(null);
        if (chief == null) {
            throw new RuntimeException();
            //todo err
        }
        return mapperFacade.map(chief, EmployeeView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeView> findByFields(EmployeeView employeeView) {
        List<Employee> list = employeeRepository.findAll(
                Specifications.findBy(
                        employeeView,
                        officeService.getById(employerView.getOfficeId()),
                        citizenshipService.getByCode(employerView.getCitizenshipCode()),
                        documentService.getByName(employerView.getDocName())
                )
        );
        return null;
    }

    @Override
    @Transactional
    public EmployeeView transfer(int employerId, int departmentId) {
        Employee employee = findById(employerId);
        Department department = departmentService.findById(departmentId);
        //todo check null empl&dep
        employee.setDepartment(department);
        return mapperFacade.map(employee, EmployeeView.class);
    }

    @Override
    @Transactional
    public List<EmployeeView> transferAll(int sourceDepartmentId, int destinationDepartmentId) {
        Department sourceDepartment = departmentService.findById(sourceDepartmentId);
        Department destinationDepartment = departmentService.findById(destinationDepartmentId);
        List<Employee> employees = employeeRepository.findAllByDepartment(sourceDepartment);
        employees.forEach(employee -> employee.setDepartment(destinationDepartment));
        // todo doubled chief
        return mapperFacade.mapAsList(employees, EmployeeView.class);
    }

    private Employee findById(int id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException();
            //todo
        }
        return optional.get();
    }

    public static Specification<Employee> findBy(EmployeeView employeeView, Integer positionId, Integer departmentId) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
//                try {
                    if (positionId != null) {
                        predicates.add(cb.equal(root.get("position"), positionId));
                    }
                    if (departmentId != null) {
                        predicates.add(cb.equal(root.get("department"), departmentId));
                    }
//                } catch (NumberFormatException ex) {
//                    throw new CantFindByParam(String.format(" wrong convert officeId=%s or docCode=%s or citizenshipCode=%s",
//                            employerView.getOfficeId(), employerView.getDocCode(), employerView.getCitizenshipCode()), ex);
//                }
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
