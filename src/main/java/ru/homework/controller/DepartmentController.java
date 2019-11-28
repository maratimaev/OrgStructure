package ru.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homework.dto.DepartmentView;
import ru.homework.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView findByName(@PathVariable String name) {
        return departmentService.findByName(name);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public void create(@RequestBody DepartmentView departmentView) {
        departmentService.create(departmentView);
    }
}