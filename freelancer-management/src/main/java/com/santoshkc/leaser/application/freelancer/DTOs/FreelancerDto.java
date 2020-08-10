package com.santoshkc.leaser.application.freelancer.DTOs;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FreelancerDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
}
