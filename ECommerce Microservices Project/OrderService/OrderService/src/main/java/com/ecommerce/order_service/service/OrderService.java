package com.ecommerce.order_service.service;

import com.ecommerce.order_service.dao.OrderRepo;
import com.ecommerce.order_service.entity.OrderEntity;
import com.ecommerce.order_service.entity.Product;
import com.ecommerce.order_service.feignclients.ProductClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductClient productClient;

    public Long getNextId() {
        OrderEntity lastUser = orderRepo.findTopByOrderByIdDesc();
        return (lastUser == null) ? 1 : lastUser.getId() + 1;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "placeOrderFallback")
    public OrderEntity placeOrder(Long productId, int quantity) {

        ResponseEntity<Product> product = productClient.getProductById(productId);
        if(product==null || product.getBody().getStock()<quantity){
            throw new RuntimeException("Product not available");
        }else {
            Product prod = product.getBody();
            OrderEntity orderEntity = new OrderEntity();
            productClient.decrement(productId, quantity);
            orderEntity.setId(getNextId());
            orderEntity.setProductId(productId);
            orderEntity.setQuantity(quantity);
            orderEntity.setTotalAmount(prod.getPrice() * quantity);
            orderEntity.setStatus("PLACED");
            return orderRepo.save(orderEntity);
        }


    }
    public OrderEntity placeOrderFallback(Long productId, int quantity, Throwable t) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(-1L);
        orderEntity.setStatus("FAILED");
        orderEntity.setTotalAmount(0);
        orderEntity.setQuantity(0);
        return orderEntity;
    }
}
