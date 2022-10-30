package com.portfolio.id.bor.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 50, message = "Ingreso inválido")
    private String name;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 50, message = "Ingreso inválido")
    private String surname;

    @NotNull
    private int age;

    @NotNull
    @Size(min = 1, max = 50, message = "Ingreso inválido")
    private String tittle;

    @NotNull
    private String img;

    @NotNull
    @Size(min = 1, max = 67000, message = "Ingreso inválido")
    private String description;

    public Person() {
    }

    public Person(final String name, final String surname, final int age, final String tittle, final String img, final String description) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.tittle = tittle;
        this.img = img;
        this.description = description;
    }
}
