package com.ecommerce.product_service.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductDto {

        private Long id;
        private String name;
        private double price;
        private int stock;
}
