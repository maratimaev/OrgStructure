package ru.homework.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    /**
     * ID департамента
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Название департамента
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
     * Дата создания департамента
     */
    @Column(name = "creation_day", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDay;

    /**
     * Вышестоящий департамент
     */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "head_department_id")
    private Department headDepartment;

    /**
     * Подчиненные департаменты
     */
    @OneToMany(mappedBy = "headDepartment")
    private List<Department> childDepartments;

    /**
     * Сотрудники департамента
     */
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employee> employees;

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

    public Date getCreationDay() {
        return creationDay;
    }

    public void setCreationDay(Date creationDay) {
        this.creationDay = creationDay;
    }

    public Department getHeadDepartment() {
        return headDepartment;
    }

    public void setHeadDepartment(Department headDepartment) {
        this.headDepartment = headDepartment;
    }

    public List<Department> getChildDepartments() {
        return childDepartments;
    }

    public void setChildDepartments(List<Department> childDepartments) {
        this.childDepartments = childDepartments;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
