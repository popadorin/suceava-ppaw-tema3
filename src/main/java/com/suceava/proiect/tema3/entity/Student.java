package com.suceava.proiect.tema3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "FirstName is mandatory")
    String firstName;

    @NotNull(message = "LastName is mandatory")
    String lastName;



}
