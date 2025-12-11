package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dao.ProductRepo;
import com.ecommerce.product_service.dto.ProductDto;
import com.ecommerce.product_service.entity.ProductEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Long getNextId() {
        ProductEntity lastUser = productRepo.findTopByOrderByIdDesc();
        return (lastUser == null) ? 1 : lastUser.getId() + 1;
    }
    public String createProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setStock(productDto.getStock());
        productEntity.setId(getNextId());
        productEntity = productRepo.save(productEntity);
        if (productEntity.getId() != null) {
            return "Product created successfully with ID: " + productEntity.getId();
        } else {
            return "Failed to create product.";
        }
    }

    public ProductEntity getProductById(Long id) {

        ProductEntity productEntity = productRepo.findById(id).orElse(null);
        if (productEntity != null) {
            return  productEntity;
        } else {
            return new ProductEntity();
        }
    }

    @Transactional
    public boolean decrementStock(Long id, int qty) {
        Optional<ProductEntity> op = productRepo.findById(id);
        if(op.isEmpty()) return false;
        ProductEntity p = op.get();
        if(p.getStock() < qty) return false;
        p.setStock(p.getStock() - qty);
        productRepo.save(p);
        return true;
    }

    public Object getAllProducts() {
        return productRepo.findAll();
    }
}
