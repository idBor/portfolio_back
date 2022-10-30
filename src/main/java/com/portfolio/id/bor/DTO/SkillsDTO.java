package com.portfolio.id.bor.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SkillsDTO {

    @NotBlank
    private String name;

    //private String img_skill;
    @NotBlank
    private int percentage;

    public SkillsDTO() {
    }

    public SkillsDTO(final String name, final int percentage) {
        this.name = name;
        this.percentage = percentage;
    }
}
