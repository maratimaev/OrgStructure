package ru.homework.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.dto.DepartmentView;
import ru.homework.mapper.MapperFacade;
import ru.homework.model.Department;
import ru.homework.repository.DepartmentRepository;
import ru.homework.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
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
        return mapperFacade.map(department, DepartmentView.class);
    }

    @Override
    @Transactional
    public void create(DepartmentView departmentView) {
        Department department = mapperFacade.map(departmentView, Department.class);
        department.setHeadDepartment(findById(departmentView.getHeadDepartmentId()));
        departmentRepository.saveAndFlush(department);
    }

    @Override
    @Transactional
    public DepartmentView update(DepartmentView departmentView) {
        Department department = findById(departmentView.getId());
        if (!department.getName().equals(departmentView.getName())) {
            //todo error doubled name
            if(departmentRepository.findDepartmentByName(departmentView.getName()) == null) {
                department.setName(departmentView.getName());
            }
        }
        if (department.getHeadDepartment().getId() != (departmentView.getHeadDepartmentId())) { //todo check for null
            for(Department child : department.getChildDepartments()) {
                child.setHeadDepartment(department.getHeadDepartment());
            }
            Department headDepartment = findById(departmentView.getHeadDepartmentId()); //todo check for department existence
            department.setHeadDepartment(headDepartment);
            department.setChildDepartments(null);
        }
        departmentRepository.saveAndFlush(department);
        DepartmentView departmentView1 = mapperFacade.map(department, DepartmentView.class);
        return departmentView1;
    }

    @Override
    @Transactional
    public void delete(int id) {
        Department department = findById(id);
        departmentRepository.delete(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentView> findChildDepartments(int id, boolean allHierarchy) {
        List<Department> childDepartments = new ArrayList<>();
        Department department = findById(id);
        if (allHierarchy) {
            childDepartments = getAllChilds(department, childDepartments);
        } else {
            childDepartments = department.getChildDepartments();
        }
        List<DepartmentView> departmentViews = mapperFacade.mapAsList(childDepartments, DepartmentView.class);
        return departmentViews;
    }

    @Override
    public List<DepartmentView> findHeadDepartments(int id) {
        Department department = findById(id);
        Department headDepartment = department.getHeadDepartment();
        List<Department> headDepartments = new ArrayList<>();
        while (headDepartment != null) {
            headDepartments.add(headDepartment);
            headDepartment = headDepartment.getHeadDepartment();
        }
        List<DepartmentView> departmentViews = mapperFacade.mapAsList(headDepartments, DepartmentView.class);
        return departmentViews;
    }

    @Override
    public Department findById(int id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException();
            //todo
        }
        return optional.get();
    }

    private List<Department> getAllChilds(Department department, List<Department> result) {
        List<Department> departments = department.getChildDepartments();
        result.addAll(departments);
        for(Department dep: departments) {
            getAllChilds(dep, result);
        }
        return result;
    }
}
