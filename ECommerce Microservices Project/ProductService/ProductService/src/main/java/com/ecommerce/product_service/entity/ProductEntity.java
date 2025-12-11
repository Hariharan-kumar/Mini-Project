package com.ecommerce.product_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private double price;
    private int stock;

}
