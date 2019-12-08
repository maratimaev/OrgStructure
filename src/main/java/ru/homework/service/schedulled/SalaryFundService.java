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

@Configuration
@EnableScheduling
public class SalaryFundService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SalaryRepository salaryRepository;

    @Scheduled(cron = "5 * * * * ?")
    public void reportCurrentTime() {
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

    @Transactional(readOnly = true)
    public Salary get(int id) {
        Optional<Salary> optional = salaryRepository.findById(id);
        if (!optional.isPresent()) {
            throw new CustomException(String.format("Фонд оплаты департамента c id = %s не найден", id));
        }
        return optional.get();
    }

    @Transactional
    public void delete(Salary salary) {
        salaryRepository.delete(salary);
    }
}
