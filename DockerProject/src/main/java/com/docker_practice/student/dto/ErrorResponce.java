package com.docker_practice.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponce {
    private String message;
    private int status;
    private LocalDateTime time;
}
