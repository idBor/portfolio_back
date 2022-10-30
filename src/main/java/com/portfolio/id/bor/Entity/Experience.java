package com.portfolio.id.bor.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 1, max = 50, message = "Ingreso inválido")
    private String name;

    private String dateStart;

    private String dateEnd;

    @Size(min = 1, max = 67000, message = "Ingreso inválido")
    private String description;

    public Experience() {
    }

    public Experience(String name, String dateStart, String dateEnd, String description) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}
