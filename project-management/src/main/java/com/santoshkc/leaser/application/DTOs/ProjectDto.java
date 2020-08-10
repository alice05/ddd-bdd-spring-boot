package com.santoshkc.leaser.application.DTOs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class ProjectDto {
    private Long project_id;
    @NotBlank
    private String project_name;
    @Positive
    private Long customer_id;
}
