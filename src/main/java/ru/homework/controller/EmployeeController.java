package ru.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.dto.EmployeeView;
import ru.homework.dto.profile.InputProfile;
import ru.homework.dto.result.Success;
import ru.homework.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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
    public Success create(@RequestBody @Validated({InputProfile.Create.class}) EmployeeView employeeView) {
         employeeService.create(employeeView);
         return new Success();
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView update(@RequestBody @Validated({InputProfile.Update.class}) EmployeeView employeeView) {
        return employeeService.update(employeeView);
    }

    @PutMapping(value = "/dismiss/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView dismiss(@PathVariable int employerId, @RequestBody @Validated({InputProfile.Dismiss.class}) EmployeeView employeeView) {
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
