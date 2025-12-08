package com.ecommerce.product_service.dao;

import com.ecommerce.product_service.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<ProductEntity,Long> {
    ProductEntity findTopByOrderByIdDesc();
}
