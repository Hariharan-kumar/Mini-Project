package com.ecommerce.security.dao;

import com.ecommerce.security.entity.AuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends MongoRepository<AuthEntity, Long> {

    AuthEntity findByEmail(String email);

    AuthEntity findTopByOrderByIdDesc();
}
