package com.santoshkc.leaser.infrastructure.web.timesheet;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class TimeSheetEventStore {
    private static final String TOPIC_EXCHANGE_NAME = "timesheet_exchange";
    private final RabbitTemplate rabbitTemplate;

    public TimeSheetEventStore(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEvent(String timeSheetEvent) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME,
                "timesheet.entered",
                timeSheetEvent);
    }
}
