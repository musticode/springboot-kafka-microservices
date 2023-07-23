package com.example.orderservice.controller;

import com.example.basedomains.payload.Order;
import com.example.basedomains.payload.OrderEvent;
import com.example.orderservice.kafka.OrderProducer;
import com.example.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderProducer orderProducer;
    private final OrderService orderService;


    public OrderController(OrderProducer orderProducer, OrderService orderService){
        this.orderProducer = orderProducer;
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public String order(@RequestBody Order order){
        return orderService.placeOrder(order);
    }


    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return "Order placed successfully...";
    }

}
