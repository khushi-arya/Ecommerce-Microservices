package com.demo.paymentservice.Entity;

import com.demo.paymentservice.Enum.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    private Long id;

    private Long orderId;

    private Double amount;

    private String transactionId;


    private PaymentStatus status;

    private LocalDateTime createdAt;
}
