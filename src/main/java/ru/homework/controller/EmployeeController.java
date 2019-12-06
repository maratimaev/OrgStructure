package ru.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.dto.DepartmentView;
import ru.homework.dto.EmployeeView;
import ru.homework.dto.profile.InputProfile;
import ru.homework.model.Department;
import ru.homework.service.DepartmentService;
import ru.homework.service.EmployeeService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/get/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView get(@PathVariable int employerId) {
        return employeeService.get(employerId);
    }

    @GetMapping(value = "/getChiefFor/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView getChief(@PathVariable int employerId) {
        return employeeService.getChief(employerId);
    }

    @GetMapping(value = "/department/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> findEmployersInDepartment(@PathVariable int departmentId) {
        return employeeService.findEmployerViewsInDepartment(departmentId);
    }

    @GetMapping(value = "/findByFields", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> findByFields(@RequestBody EmployeeView employeeView) {
        return employeeService.findByFields(employeeView);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody @Validated({InputProfile.Create.class}) EmployeeView employeeView) {
         employeeService.create(employeeView);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView update(@RequestBody EmployeeView employeeView) {
        return employeeService.update(employeeView);
    }

    @PutMapping(value = "/dismiss/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView dismiss(@PathVariable int employerId, @RequestBody EmployeeView employeeView) {
        return employeeService.dismiss(employerId, employeeView.getDismissalDay());
    }

    @PutMapping(value = "/{employerId}/transferToDepartment/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView transfer(@PathVariable int employerId, @PathVariable int departmentId) {
        return employeeService.transfer(employerId, departmentId);
    }

    @PutMapping(value = "/transferAll", params = {"sourceDepartmentId", "destinationDepartmentId"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> transferAll(@RequestParam int sourceDepartmentId, @RequestParam int destinationDepartmentId) {
        return employeeService.transferAll(sourceDepartmentId, destinationDepartmentId);
    }

}
