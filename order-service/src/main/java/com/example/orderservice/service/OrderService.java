package com.example.orderservice.service;

import com.example.basedomains.payload.Order;
import com.example.basedomains.payload.OrderEvent;
import com.example.orderservice.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer orderProducer;


    public String placeOrder(Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order is pending state");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return "Order placed...";
    }



}
