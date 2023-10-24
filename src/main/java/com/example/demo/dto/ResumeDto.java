package com.example.demo.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ResumeDto {
    private Long id;
    private Long specialistId;
    private String header;

    @Past(message = "Time  must be in the past or current")
    private Timestamp timeOfResume;

    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String resumeDescription;
    private Long categoryId;
}
