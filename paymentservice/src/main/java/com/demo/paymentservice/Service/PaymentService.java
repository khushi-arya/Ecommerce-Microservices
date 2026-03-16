package com.demo.paymentservice.Service;

import com.demo.paymentservice.Client.OrderClient;
import com.demo.paymentservice.DTO.PaymentRequest;
import com.demo.paymentservice.DTO.PaymentResponse;
import com.demo.paymentservice.Entity.Payment;
import com.demo.paymentservice.Enum.PaymentStatus;
import com.demo.paymentservice.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;


    public PaymentResponse processPayment(PaymentRequest request) {

        // 🔁 Idempotency check
        if (paymentRepository.findByTransactionId(request.getTransactionId()).isPresent()) {
            return new PaymentResponse(null, "FAILED", "Duplicate transaction");
        }

        // 💳 Simulate payment logic
        boolean paymentSuccess = request.getAmount() > 0;

        PaymentStatus status = paymentSuccess ?
                PaymentStatus.SUCCESS :
                PaymentStatus.FAILED;

        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .transactionId(request.getTransactionId())
                .status(status)
                .createdAt(LocalDateTime.now())
                .build();

        Payment saved = paymentRepository.save(payment);

        // 🔄 Update order status via Feign
        if (paymentSuccess) {
            orderClient.updateOrderStatus(request.getOrderId(), "PAID");
        } else {
            orderClient.updateOrderStatus(request.getOrderId(), "CANCELLED");
        }



        return new PaymentResponse(
                saved.getId(),
                status.name(),
                paymentSuccess ? "Payment Successful" : "Payment Failed"
        );
    }
}