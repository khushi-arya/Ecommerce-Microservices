package com.demo.productservice.Service;

import com.demo.productservice.DTO.ProductRequest;
import com.demo.productservice.DTO.ProductResponse;
import com.demo.productservice.Entity.Product;
import com.demo.productservice.Exception.ProductNotFoundException;
import com.demo.productservice.Repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating product: {}", request.getName());
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .build();
        Product saved = repository.save(product);

        log.info("Product saved with id {}", saved.getId());
        return mapToResponse(saved);
    }

    public Page<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findAll(pageable);
        if(products.isEmpty()){
            throw new ProductNotFoundException("No products available");
        }
        return repository.findAll(pageable)
                .map(this::mapToResponse);
    }



}