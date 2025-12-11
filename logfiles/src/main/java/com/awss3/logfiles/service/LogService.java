package com.awss3.logfiles.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class LogService {

    private final S3Client s3Client;
    private static final String FILE_NAME = "logs/AttendanceCheckInLogs.txt";

    @Value("${aws.s3.bucket}")
    private String bucket;

    public void appendLogToS3(String name, String email) {

        String existingData = "";

        // STEP 1 → Read existing file
        try {
            existingData = new String(
                    s3Client.getObjectAsBytes(
                            GetObjectRequest.builder()
                                    .bucket(bucket)
                                    .key(FILE_NAME)
                                    .build()
                    ).asByteArray(),
                    StandardCharsets.UTF_8
            );
        } catch (NoSuchKeyException e) {
            existingData = ""; // file does not exist
        }

        // STEP 2 → Prepare today's section data
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String header = "----------------------------" + formattedDate + "-----------------------------------\n"
                + "----------------------------------------------------------------------------\n";

        String footer = "===============END-" + formattedDate + "==================\n\n";

        // create log entry
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh.mm.ss a");
        String time = LocalDateTime.now().format(timeFormat);
        String logEntry = name + " - " + email + " - " + today + " - " + time;

        // STEP 3 → Check if today's section exists
        String todayStartMarker = "----------------------------" + formattedDate;
        String todayEndMarker = "===============END-" + formattedDate;

        String updatedContent;

        if (existingData.contains(todayStartMarker)) {

            // STEP 3A → Append inside today's section
            int endIndex = existingData.indexOf(todayEndMarker);

            String before = existingData.substring(0, endIndex);
            String after = existingData.substring(endIndex);

            // count existing logs for numbering
            int count = before.split("\n").length - before.split(todayStartMarker).length;

            String newEntry = (count + 1) + ". " + logEntry + "\n";

            updatedContent = before + newEntry + after;

        } else {

            // STEP 3B → Create new date section at bottom
            updatedContent = existingData
                    + header
                    + "1. " + logEntry + "\n"
                    + footer;
        }

        // STEP 4 → Upload back to S3
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(FILE_NAME)
                        .build(),
                RequestBody.fromInputStream(
                        new ByteArrayInputStream(updatedContent.getBytes(StandardCharsets.UTF_8)),
                        updatedContent.length()
                )
        );
    }
}
