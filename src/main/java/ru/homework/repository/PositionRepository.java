package ru.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.model.Department;
import ru.homework.model.Employee;
import ru.homework.model.Position;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
