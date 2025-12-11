package com.ecommerce.security.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class AuthDto {
    private Long id;
    private String email;
    private String password;
}
