package com.santoshkc.leaser;

import com.santoshkc.leaser.infrastructure.web.TimesheetEventStoreListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final String TOPIC_EXCHANGE_NAME = "timesheet_exchange";
    private final String QUEUE_NAME = "timesheet_queue";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("timesheet.#");
    }

    @Bean
    SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory, MessageListenerAdapter adapter) {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(adapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(TimesheetEventStoreListener listener) {
        return new MessageListenerAdapter(listener, "timeSheetEntered");
    }
}
