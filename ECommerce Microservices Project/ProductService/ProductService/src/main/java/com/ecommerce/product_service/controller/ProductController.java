package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ProductDto;
import com.ecommerce.product_service.entity.ProductEntity;
import com.ecommerce.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(service.createProduct(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok((service.getProductById(id)));
    }
    @GetMapping("/{id}/decrement-stock")
    public ResponseEntity<String> decrement(@PathVariable Long id, @RequestParam int qty) {
        boolean ok = service.decrementStock(id, qty);
        if(ok) return ResponseEntity.ok("OK");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Insufficient stock or not found");
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }
}
