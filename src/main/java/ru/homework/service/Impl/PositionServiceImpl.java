package ru.homework.service.Impl;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.dto.EmployeeView;
import ru.homework.mapper.MapperFacade;
import ru.homework.model.Department;
import ru.homework.model.Employee;
import ru.homework.model.Position;
import ru.homework.repository.EmployeeRepository;
import ru.homework.repository.PositionRepository;
import ru.homework.service.DepartmentService;
import ru.homework.service.EmployeeService;
import ru.homework.service.PositionService;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position findById(int id) {
        Optional<Position> optionalPosition = positionRepository.findById(id);
        if (!optionalPosition.isPresent()) {
            throw new RuntimeException();
            //todo
        }
        return optionalPosition.get();
    }
}


