package com.portfolio.id.bor.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EducationDTO {

    private Long id;

    @NotBlank
    private String degree;

    @NotBlank
    private String dateEnd;

    @NotBlank
    private String description;

    public EducationDTO() {
    }

    public EducationDTO(String degree, String dateEnd, String description) {
        this.degree = degree;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}
