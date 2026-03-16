package com.demo.cartservice.DTO;

import lombok.Data;

@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private String imageUrl;
}