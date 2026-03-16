package com.demo.inverntoryservice.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Inventory")
public class Inventory {
    @Id
    private Long productId;   // Same as Product Service ID

    private Integer quantity;
}
