package com.demo.inverntoryservice.Repository;

import com.demo.inverntoryservice.Entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, Long> {

}
