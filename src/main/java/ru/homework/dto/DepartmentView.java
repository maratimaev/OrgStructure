package ru.homework.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class DepartmentView {

    @Pattern(regexp = "\\b(?!(?:0)\\b)\\d{1,9}\\b")
    private int id;

    @NotEmpty
    @Size(max = 100)
    private String name;

//    @NotEmpty
    private Date creationDay;

    @Pattern(regexp = "\\b(?!(?:0)\\b)\\d{1,9}\\b")
    private int headDepartmentId;

    private String chief;

    private int employersCount;

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

    public Date getCreationDay() {
        return creationDay;
    }

    public void setCreationDay(Date creationDay) {
        this.creationDay = creationDay;
    }

    public int getHeadDepartmentId() {
        return headDepartmentId;
    }

    public void setHeadDepartmentId(int headDepartmentId) {
        this.headDepartmentId = headDepartmentId;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public int getEmployersCount() {
        return employersCount;
    }

    public void setEmployersCount(int employersCount) {
        this.employersCount = employersCount;
    }
}
