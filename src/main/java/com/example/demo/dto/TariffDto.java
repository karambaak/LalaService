package com.example.demo.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TariffDto {
    private Long id;

    private String tariffName;

    @NotEmpty(message = "Cost is required")
    @DecimalMin(value = "0.0", message = "Cost must be greater than or equal to 0")
    private Double cost;

    private Boolean availability;
}
