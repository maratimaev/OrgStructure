package ru.homework.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.dto.DepartmentView;
import ru.homework.dto.profile.InputProfile;
import ru.homework.dto.profile.OutputProfile;
import ru.homework.dto.result.Success;
import ru.homework.service.DepartmentService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @JsonView(OutputProfile.DepartmentInfo.class)
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView get(@PathVariable int id) {
        return departmentService.get(id);
    }

    @GetMapping(value = "/salaryFund/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal salaryFund(@PathVariable int id) {
        return departmentService.salaryFund(id);
    }

    @JsonView(OutputProfile.Short.class)
    @GetMapping(value = "/childDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findChildDepartments(@PathVariable int id) {
        return departmentService.findChildDepartments(id, false);
    }

    @JsonView(OutputProfile.Short.class)
    @GetMapping(value = "/allChildDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findAllChildDepartments(@PathVariable int id) {
        return departmentService.findChildDepartments(id, true);
    }

    @JsonView(OutputProfile.Short.class)
    @GetMapping(value = "/headDepartments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findHeadDepartments(@PathVariable int id) {
        return departmentService.findHeadDepartments(id);
    }

    @JsonView(OutputProfile.Short.class)
    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView findByName(@RequestBody @Validated({InputProfile.Name.class}) DepartmentView departmentView) {
        return departmentService.findByName(departmentView.getName());
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Success create(@RequestBody @Validated({InputProfile.Create.class}) DepartmentView departmentView) {
        departmentService.create(departmentView);
        return new Success();
    }

    @JsonView(OutputProfile.Short.class)
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView update(@RequestBody @Validated({InputProfile.Update.class}) DepartmentView departmentView) {
        return departmentService.update(departmentView);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Success delete(@PathVariable int id) {
        departmentService.delete(id);
        return new Success();
    }
}