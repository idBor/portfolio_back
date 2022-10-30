package com.portfolio.id.bor.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ExperienceDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String dateStart;

    @NotBlank
    private String dateEnd;

    @NotBlank
    private String description;

    public ExperienceDTO() {
    }

    public ExperienceDTO(String name, String dateStart, String dateEnd, String description) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}
