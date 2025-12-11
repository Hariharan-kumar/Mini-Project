package com.ecommerce.security.controller;


import com.ecommerce.security.dto.AuthDto;
import com.ecommerce.security.entity.AuthEntity;
import com.ecommerce.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthEntity registerUser(@RequestBody AuthDto authDto) {
        return authService.registerUser(authDto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthDto authDto) {
        return authService.loginUser(authDto);
    }
}
