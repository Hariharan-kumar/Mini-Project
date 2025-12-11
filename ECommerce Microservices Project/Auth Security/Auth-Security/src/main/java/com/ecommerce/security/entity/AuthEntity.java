package com.ecommerce.security.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.validation.annotation.Validated;

@Data
@Document(collection = "user_credentials")
public class AuthEntity {
    @MongoId(FieldType.INT64)
    private Long id;
    private String email;
    private String password;


}