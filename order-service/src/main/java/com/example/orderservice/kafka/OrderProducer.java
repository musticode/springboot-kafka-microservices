package com.example.orderservice.kafka;

import com.example.basedomains.payload.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducer {
//    private static Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private final NewTopic topic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent event){
        log.info(String.format("Order event :  %s", event.toString()));

        // create message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }




}
