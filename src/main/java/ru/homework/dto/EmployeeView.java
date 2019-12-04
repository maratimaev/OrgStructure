package ru.homework.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeView {

    private int id;

    private String name;

    private String secondName;

    private String middleName;

    private boolean sex;

    private Date birthday;

    private String phoneNumber;

    private String email;

    private Date employmentDay;

    private Date dismissalDay;

    private BigDecimal salary;

    private boolean chief;

    private int positionId;

    private int departmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
