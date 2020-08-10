package com.santoshkc.leaser.infrastructure.database;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class CustomerProjection {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String fullName;
}
