package ru.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findDepartmentByName(String name);
}
