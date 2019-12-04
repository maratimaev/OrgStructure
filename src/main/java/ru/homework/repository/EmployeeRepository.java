package ru.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
}
