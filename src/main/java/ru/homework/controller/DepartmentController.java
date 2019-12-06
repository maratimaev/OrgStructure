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

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView get(@PathVariable int id) {
        return departmentService.get(id);
    }

    @GetMapping(value = "/salaryFund/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal salaryFund(@PathVariable int id) {
        return departmentService.salaryFund(id);
    }

    @GetMapping(value = "/childDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findChildDepartments(@PathVariable int id) {
        return departmentService.findChildDepartments(id, false);
    }

    @GetMapping(value = "/allChildDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findAllChildDepartments(@PathVariable int id) {
        return departmentService.findChildDepartments(id, true);
    }

    @GetMapping(value = "/headDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findHeadDepartments(@PathVariable int id) {
        return departmentService.findHeadDepartments(id);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView findByName(@RequestBody DepartmentView departmentView) {
        return departmentService.findByName(departmentView.getName());
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody DepartmentView departmentView) {
        departmentService.create(departmentView);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView update(@RequestBody DepartmentView departmentView) {
        return departmentService.update(departmentView);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        departmentService.delete(id);
    }
}