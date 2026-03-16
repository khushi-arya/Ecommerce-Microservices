package com.demo.productservice.Controller;

import com.demo.productservice.DTO.ProductRequest;
import com.demo.productservice.DTO.ProductResponse;
import com.demo.productservice.Entity.Product;
import com.demo.productservice.Repo.ProductRepository;
import com.demo.productservice.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductRepository repository;


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createProduct(request));
    }



    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return service.getAllProducts(page, size);
    }
}