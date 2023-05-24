package com.example.basedomains.payload;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEvent {
    private String message;
    private String status;
    private Order order;

}
