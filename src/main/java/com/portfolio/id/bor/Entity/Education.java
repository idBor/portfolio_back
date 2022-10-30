package com.portfolio.id.bor.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 1, max = 50, message = "Ingreso inválido")
    private String degree;
    private String dateEnd;

    @Size(min = 1, max = 67000, message = "Ingreso inválido")
    private String description;

    public Education() {
    }

    public Education(final String degree, final String dateEnd, final String description) {
        this.degree = degree;
        this.dateEnd = dateEnd;
        this.description = description;
    }

}
