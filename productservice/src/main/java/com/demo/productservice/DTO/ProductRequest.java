package com.demo.productservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    @Positive
    private Double price;
    private Integer stock;
    private String category;
    private String imageUrl;
}
