package ru.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homework.dto.DepartmentView;
import ru.homework.service.DepartmentService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView findByName(@RequestBody DepartmentView departmentView) {
        return departmentService.findByName(departmentView.getName());
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody DepartmentView departmentView) {
        departmentService.create(departmentView);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView update(@RequestBody DepartmentView departmentView) {
        DepartmentView departmentView1 = departmentService.update(departmentView);
        return departmentView1;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        departmentService.delete(id);
    }

    @GetMapping(value = "/childDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findChildDepartments(@PathVariable int id) {
        List<DepartmentView> departmentViews = departmentService.findChildDepartments(id, false);
        return departmentViews;
    }

    @GetMapping(value = "/allChildDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findAllChildDepartments(@PathVariable int id) {
        List<DepartmentView> departmentViews = departmentService.findChildDepartments(id, true);
        return departmentViews;
    }

    @GetMapping(value = "/headDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findHeadDepartments(@PathVariable int id) {
        List<DepartmentView> departmentViews = departmentService.findHeadDepartments(id);
        return departmentViews;
    }
}