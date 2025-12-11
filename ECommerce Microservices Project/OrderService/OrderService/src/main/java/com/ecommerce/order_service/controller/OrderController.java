package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.entity.OrderEntity;
import com.ecommerce.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public OrderEntity placeOrder(long productId, int quantity) {
        return orderService.placeOrder(productId, quantity);
    }

}
