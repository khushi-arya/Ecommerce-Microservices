package com.demo.orderservice.Repository;

import com.demo.orderservice.Entity.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemReop extends MongoRepository<OrderItem,String> {
}
