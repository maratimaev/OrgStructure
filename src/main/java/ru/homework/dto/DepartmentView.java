package ru.homework.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import ru.homework.dto.profile.InputProfile;
import ru.homework.dto.profile.OutputProfile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class DepartmentView {

    @NotEmpty(groups = {InputProfile.Update.class})
    @ApiModelProperty(notes = "id департамента")
    private int id;

    @JsonView(OutputProfile.Short.class)
    @NotEmpty(groups = {InputProfile.Create.class, InputProfile.Name.class})
    @Size(max = 100, groups = {InputProfile.Create.class, InputProfile.Update.class})
    @ApiModelProperty(notes = "Название департамента")
    private String name;

    @JsonView(OutputProfile.Short.class)
    @NotEmpty(groups = {InputProfile.Create.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Дата создания")
    private Date creationDay;

    @JsonView(OutputProfile.Short.class)
    @NotEmpty(groups = {InputProfile.Create.class})
    @ApiModelProperty(notes = "Вышестоящий департамент")
    private int headDepartmentId;

    @JsonView(OutputProfile.DepartmentInfo.class)
    @ApiModelProperty(notes = "Начальник департамента")
    private String chief;

    @JsonView(OutputProfile.DepartmentInfo.class)
    @ApiModelProperty(notes = "Количество сотрудников")
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
