package com.example.stockservice.dto;

import com.example.stockservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private String orderId;
    private String name;
    private int qty;
    private double price;

    public static OrderDto mapToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setName(order.getName());
        orderDto.setQty(order.getQty());
        orderDto.setPrice(order.getPrice());
        return orderDto;
    }

    public static Order mapToOrderEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderId(orderDto.getOrderId());
        order.setName(orderDto.getName());
        order.setQty(orderDto.getQty());
        order.setPrice(orderDto.getPrice());
        return order;
    }
}
