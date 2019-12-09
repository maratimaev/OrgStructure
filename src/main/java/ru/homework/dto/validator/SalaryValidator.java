package ru.homework.dto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.homework.dto.EmployeeView;
import ru.homework.model.Department;
import ru.homework.model.Employee;
import ru.homework.service.DepartmentService;
import ru.homework.service.EmployeeService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Реализация аннотации @Salary
 */
public class SalaryValidator implements ConstraintValidator<Salary, EmployeeView> {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public boolean isValid(EmployeeView value, ConstraintValidatorContext context) {
        Department department = departmentService.findById(value.getDepartmentId());
        Employee chief = employeeService.findChiefInDepartment(department);
        return chief.getSalary().compareTo(value.getSalary()) >= 0;
    }
}
