package ru.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.model.Department;
import ru.homework.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    Salary findByDepartment(Department department);
}
