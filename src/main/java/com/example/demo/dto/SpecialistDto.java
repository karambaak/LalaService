package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecialistDto {
    private Long id;
    private UserDto user;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private TariffDto tariff;
}
