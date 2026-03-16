package com.demo.inverntoryservice.Service;


import com.demo.inverntoryservice.DTO.InventoryRequest;
import com.demo.inverntoryservice.DTO.InventoryResponse;
import com.demo.inverntoryservice.Entity.Inventory;
import com.demo.inverntoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    // 🔍 Check stock
    public InventoryResponse checkStock(Long productId) {

        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));

        return new InventoryResponse(
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getQuantity() > 0
        );
    }

    // ➖ Reduce stock
    public void reduceStock(InventoryRequest request) {

        Inventory inventory = inventoryRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        inventory.setQuantity(
                inventory.getQuantity() - request.getQuantity()
        );

        inventoryRepository.save(inventory);
    }

    // ➕ Add stock (Admin use)
    public Inventory addStock(InventoryRequest request) {

        Inventory inventory = inventoryRepository.findById(request.getProductId())
                .orElse(
                        Inventory.builder()
                                .productId(request.getProductId())
                                .quantity(0)
                                .build()
                );

        inventory.setQuantity(
                inventory.getQuantity() + request.getQuantity()
        );

        return inventoryRepository.save(inventory);
    }

}
