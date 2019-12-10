package ru.homework.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.dto.DepartmentView;
import ru.homework.dto.profile.InputProfile;
import ru.homework.dto.profile.OutputProfile;
import ru.homework.dto.result.Success;
import ru.homework.dto.result.Wrapper;
import ru.homework.service.DepartmentService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/department")
@Api(tags = {"Department"})
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /** Запрос информации о департаменте, включая информацию о начальнике и количестве сотрудников
     * @param departmentId департамента
     * @return json с информацией о департаменте
     */
    @JsonView(OutputProfile.DepartmentInfo.class)
    @ApiOperation(value = "Информация о департаменте", response = DepartmentView.class)
    @GetMapping(value = "/get/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView get(@PathVariable int departmentId) {
        return departmentService.get(departmentId);
    }

    /** Запрос фонта оплаты труда департамента
     * @param departmentId  департамента
     * @return json c ФОТ департамента
     */
    @ApiOperation(value = "ФОТ департамента", response = BigDecimal.class)
    @GetMapping(value = "/salaryFund/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Wrapper<BigDecimal> salaryFund(@PathVariable int departmentId) {
        return new Wrapper<>(departmentService.salaryFund(departmentId));
    }

    /** Запрос списка непосредственно подчиненных департаментов
     * @param departmentId департамента
     * @return json список департаментов
     */
    @JsonView(OutputProfile.Short.class)
    @ApiOperation(value = "Список непосредственно подчиненных департаментов", response = DepartmentView.class, responseContainer = "List")
    @GetMapping(value = "/childDepartments/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findChildDepartments(@PathVariable int departmentId) {
        return departmentService.findChildDepartments(departmentId, false);
    }

    /** Запрос списка всех подчиненных департаментов
     * @param departmentId департамента
     * @return json список департаментов
     */
    @JsonView(OutputProfile.Short.class)
    @ApiOperation(value = "Список всех подчиненных департаментов", response = DepartmentView.class, responseContainer = "List")
    @GetMapping(value = "/allChildDepartments/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findAllChildDepartments(@PathVariable int departmentId) {
        return departmentService.findChildDepartments(departmentId, true);
    }

    /** Запрос вышестоящих департаментов
     * @param departmentId департамента
     * @return json список департаментов
     */
    @JsonView(OutputProfile.Short.class)
    @ApiOperation(value = "Список вышестоящих департаментов", response = DepartmentView.class, responseContainer = "List")
    @GetMapping(value = "/headDepartments/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentView> findHeadDepartments(@PathVariable int departmentId) {
        return departmentService.findHeadDepartments(departmentId);
    }

    /** Запрос департамента по имени
     * @param departmentView департамента
     * @return json департамента
     */
    @JsonView(OutputProfile.Short.class)
    @ApiOperation(value = "Поиск департамента по имени", response = DepartmentView.class)
    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView findByName(@RequestBody @Validated({InputProfile.Name.class}) DepartmentView departmentView) {
        return departmentService.findByName(departmentView.getName());
    }

    /** Запрос на создание департамента
     * @param departmentView json департамента
     * @return json сообщение об успешном создании
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Создание департамента", response = Success.class)
    public Success create(@RequestBody @Validated({InputProfile.Create.class}) DepartmentView departmentView) {
        departmentService.create(departmentView);
        return new Success();
    }

    /** Запрос на изменение департамента
     * @param departmentView департамента
     * @return json департамента
     */
    @JsonView(OutputProfile.Short.class)
    @ApiOperation(value = "Изменение информации о департаменте", response = DepartmentView.class)
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentView update(@RequestBody @Validated({InputProfile.Update.class}) DepartmentView departmentView) {
        return departmentService.update(departmentView);
    }

    /** Запрос на удаление департамента
     * @param departmentId департамента
     * @return json сообщение об успешном удалении
     */
    @ApiOperation(value = "Удаление департамента", response = Success.class)
    @DeleteMapping(value = "/delete/{departmentId}")
    public Success delete(@PathVariable int departmentId) {
        departmentService.delete(departmentId);
        return new Success();
    }
}