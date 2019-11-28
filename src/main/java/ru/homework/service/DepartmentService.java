package ru.homework.service;

import ru.homework.dto.DepartmentView;

public interface DepartmentService {

    DepartmentView findByName(String name);
    void create(DepartmentView departmentView);
}
