package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ResumeDto {
    private long id;
    private long specialistId;
    private Timestamp timeOfResume;
    private String resumeDescription;
    private Integer categoryId;
}
