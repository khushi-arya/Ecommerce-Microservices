package com.demo.productservice.Entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;

    private String name;
    private String description;

    private Double price;
    private Integer stock;
    private String category;
    private String imageUrl;
}
