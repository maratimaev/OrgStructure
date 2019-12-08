package ru.homework.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.homework.dto.DepartmentView;
import ru.homework.dto.EmployeeView;
import ru.homework.dto.profile.InputProfile;
import ru.homework.dto.result.Success;
import ru.homework.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Api(tags = {"Employee"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Информация о сотруднике", response = EmployeeView.class)
    @GetMapping(value = "/get/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView get(@PathVariable int employerId) {
        return employeeService.get(employerId);
    }

    @ApiOperation(value = "Информация о начальнике сотрудника", response = EmployeeView.class)
    @GetMapping(value = "/getChiefFor/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView getChief(@PathVariable int employerId) {
        return employeeService.getChief(employerId);
    }

    @ApiOperation(value = "Список сотрудников в департаменте", response = EmployeeView.class, responseContainer = "List")
    @GetMapping(value = "/department/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> findEmployersInDepartment(@PathVariable int departmentId) {
        return employeeService.findEmployerViewsInDepartment(departmentId);
    }

    @ApiOperation(value = "Поиск сотрудников по комбинации полей", response = EmployeeView.class, responseContainer = "List")
    @GetMapping(value = "/findByFields", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> findByFields(@RequestBody EmployeeView employeeView) {
        return employeeService.findByFields(employeeView);
    }

    @ApiOperation(value = "Создание сотрудника", response = Success.class)
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Success create(@RequestBody @Validated({InputProfile.Create.class}) EmployeeView employeeView) {
         employeeService.create(employeeView);
         return new Success();
    }

    @ApiOperation(value = "Изменение информации о сотруднике", response = EmployeeView.class)
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView update(@RequestBody @Validated({InputProfile.Update.class}) EmployeeView employeeView) {
        return employeeService.update(employeeView);
    }

    @ApiOperation(value = "Увольнение сотрудника", response = EmployeeView.class)
    @PutMapping(value = "/dismiss/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView dismiss(@PathVariable int employerId, @RequestBody @Validated({InputProfile.Dismiss.class}) EmployeeView employeeView) {
        return employeeService.dismiss(employerId, employeeView.getDismissalDay());
    }

    @ApiOperation(value = "Перемещение сотрудника в другой департамент", response = EmployeeView.class)
    @PutMapping(value = "/{employerId}/transferToDepartment/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView transfer(@PathVariable int employerId, @PathVariable int departmentId) {
        return employeeService.transfer(employerId, departmentId);
    }

    @ApiOperation(value = "Список сотрудников, перемещаемых в другой департамент", response = EmployeeView.class, responseContainer = "List")
    @PutMapping(value = "/transferAll", params = {"sourceDepartmentId", "destinationDepartmentId"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> transferAll(@RequestParam int sourceDepartmentId, @RequestParam int destinationDepartmentId) {
        return employeeService.transferAll(sourceDepartmentId, destinationDepartmentId);
    }
}
