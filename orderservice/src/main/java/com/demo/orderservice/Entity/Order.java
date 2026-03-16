package com.demo.orderservice.Entity;

import com.demo.orderservice.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="oreder")
public class Order {

    @Id
    private String id;
    private String userId;
    private Double totalAmount;
    private OrderStatus status;
}
