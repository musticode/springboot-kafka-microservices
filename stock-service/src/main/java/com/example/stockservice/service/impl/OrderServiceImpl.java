package com.example.stockservice.service.impl;

import com.example.basedomains.payload.OrderEvent;
import com.example.stockservice.dto.OrderDto;
import com.example.stockservice.dto.OrderEventDto;
import com.example.stockservice.kafka.OrderConsumer;
import com.example.stockservice.model.Order;
import com.example.stockservice.repository.OrderRepository;
import com.example.stockservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConsumer orderConsumer;
    private final ModelMapper modelMapper;


    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent){
        log.info(String.format("Order event received in stock service = %s", orderEvent.toString()));

        // save the order event into the database
        Order order = new Order();
        order.setOrderId(orderEvent.getOrder().getOrderId());
        order.setName(orderEvent.getOrder().getName());
        order.setQty(orderEvent.getOrder().getQty());
        order.setPrice(orderEvent.getOrder().getPrice());

        orderRepository.save(order);

        //Order savedOrder = saveOrderToDataBase(orderEventDto.getOrder());
    }


    public Order saveOrderToDataBase(Order order){
        Order order1 = new Order();

        order1.setOrderId(order.getOrderId());
        order1.setName(order.getName());
        order1.setQty(order.getQty());
        order1.setPrice(order.getPrice());

        Order saved = orderRepository.save(order1);
        return saved;
    }


    @Override
    public OrderDto saveOrder(OrderDto orderDto) {


        Order order = OrderDto.mapToOrderEntity(orderDto);
        Order savedOrder = orderRepository.save(order);

        return OrderDto.mapToOrderDto(savedOrder);

    }

    private OrderDto mapToOrderDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }

    private Order mapToOrderEntity(OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }




}
