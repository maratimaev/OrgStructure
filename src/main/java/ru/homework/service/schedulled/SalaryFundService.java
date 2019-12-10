package ru.homework.service.schedulled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.exception.CustomException;
import ru.homework.model.Department;
import ru.homework.model.Employee;
import ru.homework.model.Salary;
import ru.homework.repository.DepartmentRepository;
import ru.homework.repository.SalaryRepository;
import ru.homework.service.DepartmentService;
import ru.homework.service.EmployeeService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для сохранения ФОТ департаментов, каждые 5 минут
 */
@Configuration
@EnableScheduling
public class SalaryFundService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SalaryRepository salaryRepository;

    /**
     * Сервис подсчета и записи в БД ФОТ департамента
     */
    @Scheduled(cron = "5 * * * * ?")
    public void calcFundSalary() {
        List<Department> departments = departmentService.findAll();
        for (Department department : departments) {
            BigDecimal fundSalary = new BigDecimal(0);
            Salary salary = salaryRepository.findByDepartment(department);
            List<Employee> employers = department.getEmployees();
            for (Employee employee : employers) {
                fundSalary = fundSalary.add(employee.getSalary());
            }
            salary.setSalary(fundSalary);
            salaryRepository.saveAndFlush(salary);
        }
    }

    /** Получение ФОТ департамента
     * @param department департамент
     * @return ФОТ департамента
     */
    @Transactional(readOnly = true)
    public Salary get(Department department) {
        Salary salary = salaryRepository.findByDepartment(department);
        if (salary == null) {
            throw new CustomException(String.format("Фонд оплаты департамента c departmentId = %s не найден", department.getId()));
        }
        return salary;
    }

    /** Удаление ФОТ департамента
     * @param salary департамента
     */
    @Transactional
    public void delete(Salary salary) {
        salaryRepository.delete(salary);
    }
}
