package com.santoshkc.leaser.domain.repositories.timesheet;

import com.santoshkc.leaser.domain.aggregates.timesheet.TimeSheetAggregator;

public interface TimeSheetRepository {
    void save(TimeSheetAggregator aggregator);
}
