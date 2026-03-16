package com.demo.cartservice.Repository;

import com.demo.cartservice.Entity.Cart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, Long> {

    List<Cart> findByUserId(String userId);

    Optional<Cart> findByUserIdAndProductId(String userId, String productId);

    void deleteByUserId(String userId);

    void deleteById(String id);
}