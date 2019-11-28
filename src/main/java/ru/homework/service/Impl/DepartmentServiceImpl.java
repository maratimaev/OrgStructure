package ru.homework.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.dto.DepartmentView;
import ru.homework.mapper.MapperFacade;
import ru.homework.model.Department;
import ru.homework.repository.DepartmentRepository;
import ru.homework.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    @Transactional(readOnly = true)
    public DepartmentView findByName(String name) {
        Department department = departmentRepository.findDepartmentByName(name);
        return mapperFacade.mapToDepartmentView(department, new DepartmentView());
    }

    @Override
    @Transactional
    public void create(DepartmentView departmentView) {
        Department department = mapperFacade.map(departmentView, Department.class);
        departmentRepository.saveAndFlush(department);
    }

}
