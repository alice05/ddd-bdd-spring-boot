package com.santoshkc.leaser.application.DTOs;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerDto {
    private Long id;
    @NotBlank
    private String full_name;
}
