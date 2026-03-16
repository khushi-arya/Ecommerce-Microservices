package com.demo.orderservice.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OrderItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;
    private Order order;
}
