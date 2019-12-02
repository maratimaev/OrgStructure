package ru.homework.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.dto.DepartmentView;
import ru.homework.mapper.MapperFacade;
import ru.homework.model.Department;
import ru.homework.repository.DepartmentRepository;
import ru.homework.service.DepartmentService;

import java.util.Optional;

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
        Optional<Department> optional = departmentRepository.findById(departmentView.getHeadDepartmentId());
        if (!optional.isPresent()) {
            throw new RuntimeException();
            //todo
        }
        department.setHeadDepartment(optional.get());
        departmentRepository.saveAndFlush(department);
    }

    @Override
    @Transactional
    public DepartmentView update(DepartmentView departmentView) {
        Optional<Department> optional = departmentRepository.findById(departmentView.getId());
        if (!optional.isPresent()) {
            throw new RuntimeException();
            //todo
        }
        Department department = optional.get();
        if (!department.getName().equals(departmentView.getName())) {
            //todo error doubled name
            if(departmentRepository.findDepartmentByName(departmentView.getName()) == null) {
                department.setName(departmentView.getName());
            }
        }
        departmentRepository.saveAndFlush(department);
        DepartmentView departmentView1 = mapperFacade.mapToDepartmentView(department, new DepartmentView());
        return departmentView1;
    }


}
