package ru.homework.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import ru.homework.dto.profile.InputProfile;
import ru.homework.dto.validator.DismissalDay;
import ru.homework.dto.validator.EmploymentDay;
import ru.homework.dto.validator.Salary;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@DismissalDay(groups = {InputProfile.Create.class})
@EmploymentDay(groups = {InputProfile.Create.class})
@Salary(groups = {InputProfile.Create.class})
public class EmployeeView {

    @NotEmpty(groups = {InputProfile.Update.class})
    @ApiModelProperty(notes = "id сотрудника")
    private int id;

    @NotEmpty(groups = {InputProfile.Create.class})
    @Size(max = 100, groups = {InputProfile.Create.class})
    @Pattern(regexp = "^[А-Яа-я]+$", groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Имя сотрудника")
    private String name;

    @NotEmpty(groups = {InputProfile.Create.class})
    @Size(max = 100, groups = {InputProfile.Create.class})
    @Pattern(regexp = "^[А-Яа-я]+$", groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Фамилия сотрудника")
    private String secondName;

    @Size(max = 100, groups = {InputProfile.Create.class})
    @Pattern(regexp = "^[А-Яа-я]+$", groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Отчество сотрудника")
    private String middleName;

    @NotNull(groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Пол")
    private boolean sex;

    @NotNull(groups = {InputProfile.Create.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Дата рождения")
    private Date birthday;

    @NotNull(groups = {InputProfile.Create.class})
    @Size(max = 11, groups = {InputProfile.Create.class})
    @Pattern(regexp = "^[“+-0123456789() ]+$", groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Номер телефона")
    private String phoneNumber;

    @NotNull(groups = {InputProfile.Create.class})
    @Email(groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Электронная почта")
    private String email;

    @NotNull(groups = {InputProfile.Create.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Дата трудоустройства")
    private Date employmentDay;

    @NotEmpty(groups = {InputProfile.Dismiss.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Дата увольнения")
    private Date dismissalDay;

    @NotNull(groups = {InputProfile.Create.class})
    @Digits(integer = 10, fraction = 0, groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Зарплата")
    private BigDecimal salary;

    @NotNull(groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Начальник департамента?")
    private boolean chief;

    @NotNull(groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Id должность")
    private int positionId;

    @NotNull(groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Id департамента")
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
