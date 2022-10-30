package com.portfolio.id.bor.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PersonDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private int age;

    @NotBlank
    private String tittle;

    @NotBlank
    private String img;

    @NotBlank
    private String description;

    public PersonDTO() {
    }

    public PersonDTO(String name, String surname, int age, String tittle, String img, String description) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.tittle = tittle;
        this.img = img;
        this.description = description;
    }

}
