package com.santoshkc.leaser.application.timesheet;

import com.santoshkc.leaser.domain.aggregates.timesheet.TimeSheetAggregator;
import com.santoshkc.leaser.domain.services.TimeSheetService;
import org.springframework.stereotype.Service;

@Service
public class TimeSheetApplication {

    private final TimeSheetService service;

    public TimeSheetApplication(TimeSheetService service) {
        this.service = service;
    }

    public void save(TimeSheetDto dto) {
        TimeSheetAggregator aggregator = new TimeSheetAggregator
                .TimeSheetAggregatorBuilder(null)
                .userId(dto.getUserId())
                .year(dto.getYear())
                .month(dto.getMonth())
                .hoursWorked(dto.getHoursWorked())
                .build();
        service.save(aggregator);
    }
}
