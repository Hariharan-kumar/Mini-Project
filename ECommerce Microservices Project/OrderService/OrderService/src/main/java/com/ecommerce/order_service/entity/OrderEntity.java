package com.ecommerce.order_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "orders")
public class OrderEntity {
    @MongoId(FieldType.INT64)
    private Long id;
    private Long productId;
    private int quantity;
    private double totalAmount;
    private String status;
}
