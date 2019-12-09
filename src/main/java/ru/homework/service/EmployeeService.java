package ru.homework.service;

import ru.homework.dto.EmployeeView;
import ru.homework.model.Department;
import ru.homework.model.Employee;

import java.util.Date;
import java.util.List;

/**
 * Сервис для работы с сотрудниками
 */
public interface EmployeeService {

    /** Поиск сотрудника
     * @param id сотрудника
     * @return DTO сотрудника
     */
    EmployeeView get(int id);

    /** Поиск сотрудников в департаменте
     * @param departmentId департамента
     * @return список DTO сотрудников
     */
    List<EmployeeView> findEmployerViewsInDepartment(int departmentId);

    /** Поиск сотрудников в департаменте
     * @param departmentId департамента
     * @return список моделей сотрудников
     */
    List<Employee> findEmployersInDepartment(int departmentId);

    /** Поиск начальника сотрудника
     * @param employerId сотрудник
     * @return DTO начальника
     */
    EmployeeView getChief(int employerId);

    /** Поиск сотрудника по полям (департамент, должность, имя, фамилия)
     * @param employeeView DTO сотрудника
     * @return список DTO сотрудников
     */
    List<EmployeeView> findByFields(EmployeeView employeeView);

    /** Поиск начальника в департаменте
     * @param department департамент
     * @return модель начальника
     */
    Employee findChiefInDepartment(Department department);

    /** Подсчет сотрудников в депараменте
     * @param department департамент
     * @return количество сотрудников
     */
    int countDepartmentEmployers(Department department);

    /** Создание сотрудника
     * @param employeeView DTO сотрудника
     */
    void create(EmployeeView employeeView);

    /** Изменение информации о сотруднике
     * @param employeeView DTO сотрудника
     * @return DTO сотрудника с прменными изменениями
     */
    EmployeeView update(EmployeeView employeeView);

    /** Удаление сотрудника
     * @param employerId сотрудника
     * @param dismissalDay дата увольнения
     * @return DTO сотрудника
     */
    EmployeeView dismiss(int employerId, Date dismissalDay);

    /** Перемещение сотрудника в другой департамент
     * @param employerId сотрудника
     * @param departmentId целевого департамента
     * @return DTO сотрудника с измененными данными
     */
    EmployeeView transfer(int employerId, int departmentId);

    /** Перемещение всех сотрудников в другой департамент
     * @param sourceDepartmentId исходный департамент
     * @param destinationDepartmentId целевой департамент
     * @return список DTO сотрудников
     */
    List<EmployeeView> transferAll(int sourceDepartmentId, int destinationDepartmentId);
}
