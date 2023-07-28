package com.example.stockservice.dto;


import com.example.stockservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEventDto {
    private String message;
    private String status;
    private Order order;
}
