package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Integer id;
    private String categoryName;
    private String description;
}
