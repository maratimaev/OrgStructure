package ru.homework.dto;

import ru.homework.dto.profile.InputProfile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeView {

    @Pattern(regexp = "\\b(?!(?:0)\\b)\\d{1,9}\\b")
    private int id;

    @NotEmpty(groups = {InputProfile.Create.class})
    @Size(max = 100, groups = {InputProfile.Create.class})
    @Pattern(regexp = "^[А-Яа-я]+$")
    private String name;

    @NotEmpty(groups = {InputProfile.Create.class})
    @Size(max = 100, groups = {InputProfile.Create.class})
    @Pattern(regexp = "^[А-Яа-я]+$")
    private String secondName;

    @Size(max = 100)
    @Pattern(regexp = "^[А-Яа-я]+$")
    private String middleName;

    @NotNull(groups = {InputProfile.Create.class})
    private boolean sex;

    @NotNull(groups = {InputProfile.Create.class})
    @Pattern(regexp = "[0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])")
    private Date birthday;

    @NotNull(groups = {InputProfile.Create.class})
    @Size(max = 11, groups = {InputProfile.Create.class})
    private String phoneNumber;

    @NotNull(groups = {InputProfile.Create.class})
    @Email
    private String email;

    @NotNull(groups = {InputProfile.Create.class})
    @Pattern(regexp = "[0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])")
    private Date employmentDay;

    @Pattern(regexp = "[0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])")
    private Date dismissalDay;

    @NotNull(groups = {InputProfile.Create.class})
    @Digits(integer = 10, fraction = 0)
    private BigDecimal salary;

    @NotNull(groups = {InputProfile.Create.class})
    private boolean chief;

    @NotNull(groups = {InputProfile.Create.class})
    private int positionId;

    @NotNull(groups = {InputProfile.Create.class})
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
