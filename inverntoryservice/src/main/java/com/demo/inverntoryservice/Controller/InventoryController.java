package com.demo.inverntoryservice.Controller;

import com.demo.inverntoryservice.DTO.InventoryRequest;
import com.demo.inverntoryservice.DTO.InventoryResponse;
import com.demo.inverntoryservice.Entity.Inventory;
import com.demo.inverntoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // Check stock
    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> checkStock(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.checkStock(productId));
    }

    // Reduce stock (Called by Order Service)
    @PostMapping("/reduce")
    public ResponseEntity<String> reduceStock(@RequestBody InventoryRequest request) {
        inventoryService.reduceStock(request);
        return ResponseEntity.ok("Stock reduced successfully");
    }

    // Add stock (Admin)
    @PostMapping("/add")
    public ResponseEntity<Inventory> addStock(@RequestBody InventoryRequest request) {
        return ResponseEntity.ok(inventoryService.addStock(request));
    }
}
