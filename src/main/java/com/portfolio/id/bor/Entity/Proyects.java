package com.portfolio.id.bor.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Proyects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 1, max = 50, message = "Ingreso inválido")
    private String name;

    @Size(min = 1, max = 10, message = "Ingreso inválido")
    private String dateEnd;

    @Size(min = 1, max = 225, message = "Ingreso inválido")
    private String link;

    @Size(min = 1, max = 67000, message = "Ingreso inválido")
    private String description;

    private String img;


    public Proyects() {
    }

    public Proyects(final String name, final String dateEnd, String link, final String description, final String img) {
        this.name = name;
        this.dateEnd = dateEnd;
        this.link = link;
        this.description = description;
        this.img = img;
    }
}
