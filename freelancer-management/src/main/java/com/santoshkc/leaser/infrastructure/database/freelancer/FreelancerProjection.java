package com.santoshkc.leaser.infrastructure.database.freelancer;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class FreelancerProjection {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String zipCode;
    private String city;
}
