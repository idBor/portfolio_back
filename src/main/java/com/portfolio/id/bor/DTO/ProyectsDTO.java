package com.portfolio.id.bor.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProyectsDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String dateEnd;

    @NotBlank
    private String link;

    @NotBlank
    private String description;

    @NotBlank
    private String img;

    public ProyectsDTO() {
    }

    public ProyectsDTO(final String name, final String dateEnd, String link, final String description, final String img) {
        this.name = name;
        this.dateEnd = dateEnd;
        this.link = link;
        this.description = description;
        this.img = img;
    }
}
