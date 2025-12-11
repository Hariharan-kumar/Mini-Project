package com.awss3.logfiles.controller;

import com.awss3.logfiles.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final LogService logService;


    @Value("${aws.s3.bucket}")
    private String bucket;


    @PostMapping("/save")
    public String saveUser(@RequestParam String name, @RequestParam String email) {

        logService.appendLogToS3(name, email);

        return "User Saved & Attendance Log Updated in S3!";
    }


}
