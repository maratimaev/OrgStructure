package ru.homework.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import ru.homework.dto.profile.InputProfile;
import ru.homework.dto.profile.OutputProfile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class DepartmentView {

    @NotEmpty(groups = {InputProfile.Update.class})
    private int id;

    @JsonView(OutputProfile.Short.class)
    @NotEmpty(groups = {InputProfile.Create.class, InputProfile.Name.class})
    @Size(max = 100, groups = {InputProfile.Update.class})
    private String name;

    @JsonView(OutputProfile.Short.class)
    @NotEmpty(groups = {InputProfile.Create.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDay;

    @JsonView(OutputProfile.Short.class)
    @NotEmpty(groups = {InputProfile.Create.class})
    private int headDepartmentId;

    @JsonView(OutputProfile.DepartmentInfo.class)
    private String chief;

    @JsonView(OutputProfile.DepartmentInfo.class)
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
