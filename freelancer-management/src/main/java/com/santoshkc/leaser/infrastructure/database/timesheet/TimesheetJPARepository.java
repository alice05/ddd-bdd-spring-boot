package com.santoshkc.leaser.infrastructure.database.timesheet;

import com.santoshkc.leaser.domain.aggregates.timesheet.TimeSheetAggregator;
import com.santoshkc.leaser.domain.repositories.timesheet.TimeSheetRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TimesheetJPARepository implements TimeSheetRepository {

    private final TimesheetDAO dao;

    public TimesheetJPARepository(TimesheetDAO dao) {
        this.dao = dao;
    }


    @Override
    public void save(TimeSheetAggregator aggregator) {
        TimesheetProjection projection = new TimesheetProjection();
        projection.setUserId(aggregator.getUserId());
        projection.setYear(aggregator.getYear());
        projection.setMonth(aggregator.getMonth());
        projection.setHoursWorked(aggregator.getHoursWorked());

        dao.save(projection);
    }
}
