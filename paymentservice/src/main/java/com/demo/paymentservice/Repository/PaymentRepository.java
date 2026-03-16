package com.demo.paymentservice.Repository;

import com.demo.paymentservice.Entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Long> {
    Optional<Payment> findByTransactionId(String transactionId);
}
