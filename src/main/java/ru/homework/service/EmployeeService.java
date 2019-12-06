package ru.homework.service;

import ru.homework.dto.EmployeeView;
import ru.homework.model.Department;
import ru.homework.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    EmployeeView get(int id);

    List<EmployeeView> findEmployerViewsInDepartment(int departmentId);
    List<Employee> findEmployersInDepartment(int departmentId);

    void create(EmployeeView employeeView);

    EmployeeView update(EmployeeView employeeView);

    EmployeeView dismiss(int employerId, Date dismissalDay);

    EmployeeView transfer(int employerId, int departmentId);

    List<EmployeeView> transferAll(int sourceDepartmentId, int destinationDepartmentId);

    EmployeeView getChief(int employerId);

    List<EmployeeView> findByFields(EmployeeView employeeView);

    Employee findChiefInDepartment(Department department);
    int countDepartmentEmployers(Department department);
}
