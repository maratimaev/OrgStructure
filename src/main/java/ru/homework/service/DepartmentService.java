package ru.homework.service;

import ru.homework.dto.DepartmentView;

import java.util.List;
import java.util.Set;

public interface DepartmentService {

    DepartmentView findByName(String name);
    void create(DepartmentView departmentView);
    DepartmentView update(DepartmentView departmentView);
    void delete(int id);

    List<DepartmentView> findChildDepartments(int id, boolean allHierarchy);

}
