package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TariffDto {
    private Long id;
    private String tariffName;
    private Double cost;
    private Boolean availability;
}
