package com.santoshkc.leaser.domain.services;

import com.santoshkc.leaser.domain.aggregates.timesheet.TimeSheetAggregator;
import com.santoshkc.leaser.domain.repositories.timesheet.TimeSheetRepository;
import com.santoshkc.leaser.infrastructure.web.timesheet.TimeSheetEventStore;
import org.springframework.stereotype.Service;

@Service
public class TimeSheetService {
    private final TimeSheetRepository repository;
    private final TimeSheetEventStore eventStore;

    public TimeSheetService(TimeSheetRepository repository, TimeSheetEventStore eventStore) {
        this.repository = repository;
        this.eventStore = eventStore;
    }

    public void save(TimeSheetAggregator aggregator) {
        this.repository.save(aggregator);
        eventStore.sendEvent("timesheet has been entered for userid " + aggregator.getUserId());
    }

}
