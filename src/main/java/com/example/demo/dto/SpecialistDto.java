package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecialistDto {
    private int id;
    private UserDto user;
    private String companyName;
    private TariffDto tariff;
}
