package ru.homework.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    /** Запрос информации о сотруднике
     * @param employerId  сотрудника
     * @return json сотрудника
     */
    @ApiOperation(value = "Информация о сотруднике", response = EmployeeView.class)
    @GetMapping(value = "/get/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView get(@PathVariable int employerId) {
        return employeeService.get(employerId);
    }

    /** Запрос информации о начальнике сотрудника
     * @param employerId сотрудника
     * @return json сотрудника
     */
    @ApiOperation(value = "Информация о начальнике сотрудника", response = EmployeeView.class)
    @GetMapping(value = "/getChiefFor/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView getChief(@PathVariable int employerId) {
        return employeeService.getChief(employerId);
    }

    /** Запрос сотрудников в департаменте
     * @param departmentId департамента
     * @return json список сотрудников
     */
    @ApiOperation(value = "Список сотрудников в департаменте", response = EmployeeView.class, responseContainer = "List")
    @GetMapping(value = "/department/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> findEmployersInDepartment(@PathVariable int departmentId) {
        return employeeService.findEmployerViewsInDepartment(departmentId);
    }

    /** Запрос сотрудников по комбинации имени, фамилии, должности, депаратменту
     * @param employeeView json сотрудника
     * @return json список сотрудников
     */
    @ApiOperation(value = "Поиск сотрудников по комбинации полей", response = EmployeeView.class, responseContainer = "List")
    @GetMapping(value = "/findByFields", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> findByFields(@RequestBody EmployeeView employeeView) {
        return employeeService.findByFields(employeeView);
    }

    /** Запрос на создание сотрудника
     * @param employeeView  json сотрудника
     * @return json сообщение об успешном создании
     */
    @ApiOperation(value = "Создание сотрудника", response = Success.class)
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Success create(@RequestBody @Validated({InputProfile.Create.class}) EmployeeView employeeView) {
         employeeService.create(employeeView);
         return new Success();
    }

    /** Запрос на изменение информации о сотруднике
     * @param employeeView json сотрудника
     * @return json сортудника с измененными данными
     */
    @ApiOperation(value = "Изменение информации о сотруднике", response = EmployeeView.class)
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView update(@RequestBody @Validated({InputProfile.Update.class}) EmployeeView employeeView) {
        return employeeService.update(employeeView);
    }

    /** Запрос на увольнение сотрудника
     * @param employerId сотрудника
     * @param employeeView json сотрудника с датой увольнения
     * @return json сотрудника с измененными данными
     */
    @ApiOperation(value = "Увольнение сотрудника", response = EmployeeView.class)
    @PutMapping(value = "/dismiss/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView dismiss(@PathVariable int employerId, @RequestBody @Validated({InputProfile.Dismiss.class}) EmployeeView employeeView) {
        return employeeService.dismiss(employerId, employeeView.getDismissalDay());
    }

    /** Запрос на перемещение сотрудника
     * @param employerId сотрудника
     * @param departmentId целевого департамента
     * @return json сотрудника с измененными данными
     */
    @ApiOperation(value = "Перемещение сотрудника в другой департамент", response = EmployeeView.class)
    @PutMapping(value = "/{employerId}/transferToDepartment/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeView transfer(@PathVariable int employerId, @PathVariable int departmentId) {
        return employeeService.transfer(employerId, departmentId);
    }

    /** Запрос на перемещение всех сотрудников департамента
     * @param sourceDepartmentId исходного департамента
     * @param destinationDepartmentId целевого департамента
     * @return json список перемещенных сотрудников
     */
    @ApiOperation(value = "Список сотрудников, перемещаемых в другой департамент", response = EmployeeView.class, responseContainer = "List")
    @PutMapping(value = "/transferAll", params = {"sourceDepartmentId", "destinationDepartmentId"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> transferAll(@RequestParam int sourceDepartmentId, @RequestParam int destinationDepartmentId) {
        return employeeService.transferAll(sourceDepartmentId, destinationDepartmentId);
    }
}
