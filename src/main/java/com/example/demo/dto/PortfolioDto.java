package com.example.demo.dto;

import com.example.demo.entities.Specialist;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class PortfolioDto {
    private long id;
    private long specialistId;

    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @Past(message = "Time of Portfolio must be in the past or current")
    private Timestamp timeOfPortfolio;
}
