package com.example.stockservice.service;

import com.example.stockservice.dto.OrderDto;

public interface OrderService {
    OrderDto saveOrder(OrderDto orderDto);
}
