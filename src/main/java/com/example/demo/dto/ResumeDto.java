package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ResumeDto {
    private Long id;
    private Long specialistId;
    private Timestamp timeOfResume;
    private String resumeDescription;
    private Integer categoryId;
}
