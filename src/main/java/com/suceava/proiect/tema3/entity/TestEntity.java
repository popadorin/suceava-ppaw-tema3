package com.suceava.proiect.tema3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Name is mandatory")
    String name;

    public TestEntity(String name) {
        this.name = name;
    }

    public TestEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
