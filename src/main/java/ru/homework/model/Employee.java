package ru.homework.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    /**
     * ID сотрудника
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя сотрудника
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
     * Фамилия сотрудника
     */
    @Column(name = "second_name", length = 100, nullable = false)
    private String secondName;

    /**
     * Отчество сотрудника
     */
    @Column(name = "middle_name", length = 100)
    private String middleName;

    /**
     * Пол сотрудника (мужской = true, женский = false)
     */
    @Column(name = "sex", nullable = false)
    private boolean sex = false;

    /**
     * День рождения сотрудника
     */
    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * Телефонный номер
     */
    @Column(name = "phone_number", length = 11, nullable = false)
    private String phoneNumber;

    /**
     * Почтовый адрес
     */
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    /**
     * Дата приема на работу
     */
    @Column(name = "employment_day", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date employmentDay;

    /**
     * Дата увольнения
     */
    @Column(name = "dismissal_day")
    @Temporal(TemporalType.DATE)
    private Date dismissalDay;

    /**
     * Оклад
     */
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    /**
     * Начальник сотрудника
     */
    @Column(name = "chief", nullable = false)
    private boolean chief = false;

    /**
     * Должность сотрудника
     */
    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    /**
     * Департамент сотрудника
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    /**
     * Служебное поле для механизма оптимистичных блокировок
     */
    @Version
    private int version;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEmploymentDay() {
        return employmentDay;
    }

    public void setEmploymentDay(Date employmentDay) {
        this.employmentDay = employmentDay;
    }

    public Date getDismissalDay() {
        return dismissalDay;
    }

    public void setDismissalDay(Date dismissalDay) {
        this.dismissalDay = dismissalDay;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
