package com.demo.orderservice.Client;

import com.demo.orderservice.DTO.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cartservice")
public interface CartClient {

    @GetMapping("/cart/{userId}")
    CartResponse getCart(String userid);
}
