package com.docker_practice.student.exceptionhandler;

import com.docker_practice.student.dto.ErrorResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponce> handleUserNotFound(UserNotFoundException ex){
        ErrorResponce errorResponce = new ErrorResponce(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);

    }

}
