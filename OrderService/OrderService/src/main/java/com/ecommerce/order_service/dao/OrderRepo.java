package com.ecommerce.order_service.dao;


import com.ecommerce.order_service.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<OrderEntity,Long> {

    OrderEntity findTopByOrderByIdDesc();
}
