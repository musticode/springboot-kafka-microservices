package com.example.emailservice.kafka;

import com.example.basedomains.payload.OrderEvent;
import com.example.emailservice.dto.EmailDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event is received %s", orderEvent.toString()));

        System.out.println(orderEvent.getOrder().getOrderId());
        System.out.println(orderEvent.getOrder().getName());
        System.out.println(orderEvent.getOrder().getQty());
    }

}
