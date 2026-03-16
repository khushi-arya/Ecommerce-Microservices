package com.demo.orderservice.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {
    private Long userId;
    private List<CartItemResponse> items;
    private Double totalAmount;
}
