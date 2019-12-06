package ru.homework.service;

import ru.homework.dto.DepartmentView;
import ru.homework.model.Department;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface DepartmentService {

    DepartmentView findByName(String name);
    Department findById(int id);
    void create(DepartmentView departmentView);
    DepartmentView update(DepartmentView departmentView);
    void delete(int id);

    List<DepartmentView> findChildDepartments(int id, boolean allHierarchy);

    List<DepartmentView> findHeadDepartments(int id);

    DepartmentView get(int id);

    BigDecimal salaryFund(int id);
}
