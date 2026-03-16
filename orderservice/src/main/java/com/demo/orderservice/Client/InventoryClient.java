package com.demo.orderservice.Client;

import com.demo.orderservice.DTO.InventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "inverntoryservice")
public interface InventoryClient {
    @GetMapping("/inventory/reduce")
    void reduceStock(InventoryRequest inventoryRequest);
}
