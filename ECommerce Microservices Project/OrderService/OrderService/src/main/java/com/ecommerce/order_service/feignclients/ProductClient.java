package com.ecommerce.order_service.feignclients;

import com.ecommerce.order_service.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {
    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProductById(@PathVariable("id")Long productId);
    @GetMapping("/products/{id}/decrement-stock")
    ResponseEntity<String> decrement(@PathVariable("id") Long id, @RequestParam("qty") int qty);


}

