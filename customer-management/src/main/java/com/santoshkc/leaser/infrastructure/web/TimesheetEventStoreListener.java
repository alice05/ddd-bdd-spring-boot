package com.santoshkc.leaser.infrastructure.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimesheetEventStoreListener {

     Logger logger = LoggerFactory.getLogger(TimesheetEventStoreListener.class);

    public void timeSheetEntered(String timesheetEvent) {
        logger.info("Event received at customer management");
        logger.info(timesheetEvent);
    }
}
