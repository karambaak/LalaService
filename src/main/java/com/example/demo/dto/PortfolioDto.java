package com.example.demo.dto;

import com.example.demo.entities.Specialist;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class PortfolioDto {
    private long id;
    private long specialistId;
    private String title;
    private Timestamp timeOfPortfolio;
}
