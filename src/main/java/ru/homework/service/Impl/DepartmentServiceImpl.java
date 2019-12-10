package ru.homework.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.dto.DepartmentView;
import ru.homework.dto.EmployeeView;
import ru.homework.exception.CustomException;
import ru.homework.mapper.MapperFacade;
import ru.homework.model.Department;
import ru.homework.model.Employee;
import ru.homework.repository.DepartmentRepository;
import ru.homework.service.DepartmentService;
import ru.homework.service.schedulled.SalaryFundService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeServiceImpl  employeeService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private SalaryFundService salaryFundService;

    @Override
    @Transactional(readOnly = true)
    public DepartmentView findByName(String name) {
        Department department = departmentRepository.findDepartmentByName(name);
        return mapperFacade.map(department, DepartmentView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentView get(int id) {
        Department department = findById(id);
        DepartmentView departmentView = mapperFacade.map(department, DepartmentView.class);
        Employee chief = employeeService.findChiefInDepartment(department);
        if (chief != null) {
            departmentView.setChief(chief.getName() + " " + chief.getSecondName());
        }
        int employersCount = employeeService.countDepartmentEmployers(department);
        departmentView.setEmployersCount(employersCount);
        return departmentView;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal salaryFund(int departmentId) {
        return salaryFundService.get(findById(departmentId)).getSalary();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentView> findChildDepartments(int id, boolean allHierarchy) {
        List<Department> childDepartments = new ArrayList<>();
        Department department = findById(id);
        if (allHierarchy) {
            childDepartments = getAllChilds(department, childDepartments);
        } else {
            childDepartments = department.getChildDepartments();
        }
        return mapperFacade.mapAsList(childDepartments, DepartmentView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentView> findHeadDepartments(int id) {
        Department department = findById(id);
        Department headDepartment = department.getHeadDepartment();
        List<Department> headDepartments = new ArrayList<>();
        while (headDepartment != null) {
            headDepartments.add(headDepartment);
            headDepartment = headDepartment.getHeadDepartment();
        }
        return mapperFacade.mapAsList(headDepartments, DepartmentView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Department findById(int id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new CustomException(String.format("Департамент с id = %s не найден", id));
        }
        return optional.get();
    }

    @Override
    @Transactional
    public void create(DepartmentView departmentView) {
        Department department = mapperFacade.map(departmentView, Department.class);
        department.setHeadDepartment(findById(departmentView.getHeadDepartmentId()));
        departmentRepository.saveAndFlush(department);
    }

    @Override
    @Transactional
    public DepartmentView update(DepartmentView departmentView) {
        Department department = findById(departmentView.getId());
        if (!department.getName().equals(departmentView.getName())) {
            if(departmentRepository.findDepartmentByName(departmentView.getName()) != null) {
                throw new CustomException(String.format("Департамент с названием %s уже существует", departmentView.getName()));
            }
            department.setName(departmentView.getName());
        }
        if (department.getHeadDepartment() != null && department.getHeadDepartment().getId() != departmentView.getHeadDepartmentId()) {
            for(Department child : department.getChildDepartments()) {
                child.setHeadDepartment(department.getHeadDepartment());
                departmentRepository.saveAndFlush(child);
            }
            Department headDepartment = findById(departmentView.getHeadDepartmentId());
            department.setHeadDepartment(headDepartment);
            department.setChildDepartments(null);
        }
        Department result = departmentRepository.saveAndFlush(department);
        return mapperFacade.map(result, DepartmentView.class);
    }

    @Override
    @Transactional
    public void delete(int id) {
        List<EmployeeView> employeeViews = employeeService.findEmployerViewsInDepartment(id);
        if (!employeeViews.isEmpty()) {
            throw new CustomException("В департаменте есть сотрудники");
        }
        Department department = findById(id);
        for (Department child : department.getChildDepartments()) {
            child.setHeadDepartment(department.getHeadDepartment());
            departmentRepository.saveAndFlush(child);
        }
        salaryFundService.delete(salaryFundService.get(department));
        departmentRepository.delete(department);
    }

    private List<Department> getAllChilds(Department department, List<Department> result) {
        List<Department> departments = department.getChildDepartments();
        result.addAll(departments);
        for(Department dep: departments) {
            getAllChilds(dep, result);
        }
        return result;
    }
}
