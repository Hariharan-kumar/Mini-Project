package com.ecommerce.security.service;

import com.ecommerce.security.dao.AuthRepo;
import com.ecommerce.security.dto.AuthDto;
import com.ecommerce.security.entity.AuthEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;
    public Long getNextId() {
        AuthEntity lastUser = authRepo.findTopByOrderByIdDesc();
        return (lastUser == null) ? 1 : lastUser.getId() + 1;
    }


    public AuthEntity registerUser(AuthDto authDto) {
        AuthEntity authEntity = new AuthEntity();
        authEntity.setId(getNextId());
        authEntity.setEmail(authDto.getEmail());
        authEntity.setPassword(authDto.getPassword());
        return authRepo.save(authEntity);
    }

    public String loginUser(AuthDto authDto) {
        AuthEntity login=authRepo.findByEmail(authDto.getEmail());
        if(login!=null && login.getPassword().equals( authDto.getPassword())){
            return "Login Successful";
            }else{
            return "Invalid Credentials";
 }
    }
}
