package com.santoshkc.leaser.application.timesheet;

import lombok.Data;

import javax.validation.constraints.Positive;


@Data
public class TimeSheetDto {
    private Long id;
    @Positive
    private Long userId;
    @Positive
    private Integer year;
    @Positive
    private Integer month;
    @Positive
    private Integer hoursWorked;
}
