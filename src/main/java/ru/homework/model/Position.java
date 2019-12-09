package ru.homework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "position")
public class Position {

    /**
     * ID должности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Название должности
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
     * описание должности
     */
    @Column(name = "description", length = 500)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
