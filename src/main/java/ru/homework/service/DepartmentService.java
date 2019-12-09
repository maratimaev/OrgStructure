package ru.homework.service;

import ru.homework.dto.DepartmentView;
import ru.homework.model.Department;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Сервис работы с департаментами
 */
public interface DepartmentService {

    /** Поиск департамента по имени
     * @param name название департамента
     * @return DTO департамента
     */
    DepartmentView findByName(String name);

    /** Поиск департамента по ID
     * @param id департамента
     * @return модель департамента
     */
    Department findById(int id);

    /** Поиск подчиненных департаментов
     * @param id исходного департамента
     * @param allHierarchy признак запроса (true = вся иерерхия, false = только непосредственно подчиненных)
     * @return список DTO департаментов
     */
    List<DepartmentView> findChildDepartments(int id, boolean allHierarchy);

    /** Поиск вышестоящих департаментов
     * @param id исходного департамента
     * @return список DTO департаментов
     */
    List<DepartmentView> findHeadDepartments(int id);

    /** Поиск департамента по ID
     * @param id департамента
     * @return DTO департамента
     */
    DepartmentView get(int id);

    /** Запрос фонда оплаты труда сотрудников департамента
     * @param id департамента
     * @return суммарный ФОТ
     */
    BigDecimal salaryFund(int id);

    /** Создание департамента
     * @param departmentView DTO департамента
     */
    void create(DepartmentView departmentView);

    /** Обновление информации о департаменте
     * @param departmentView  DTO департамента
     * @return DTO департамента с применными изменениями
     */
    DepartmentView update(DepartmentView departmentView);

    /** Удаление департамента
     * @param id департамента
     */
    void delete(int id);
}
