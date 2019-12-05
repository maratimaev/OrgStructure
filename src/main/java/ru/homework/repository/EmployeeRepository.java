package ru.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.homework.model.Department;
import ru.homework.model.Employee;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    List<Employee> findAllByDepartment(Department department);
}
