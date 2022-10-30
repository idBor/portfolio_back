package com.portfolio.id.bor.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 50, message = "Ingreso inválido")
    private String name;
    @NotNull
    private int percentage;

    public Skills() {
    }

    public Skills(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }
}
