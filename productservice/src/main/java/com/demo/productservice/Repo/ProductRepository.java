package com.demo.productservice.Repo;

import com.demo.productservice.DTO.ProductResponse;
import com.demo.productservice.Entity.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


}