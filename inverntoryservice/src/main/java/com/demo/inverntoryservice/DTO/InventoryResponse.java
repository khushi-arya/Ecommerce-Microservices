package com.demo.inverntoryservice.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    private Long productId;
    private Integer availableQuantity;
    private boolean inStock;
}
