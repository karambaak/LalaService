package com.example.demo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;

    @Pattern(regexp = "^[^0-9]+$", message = "Category name should not contain numbers")
    private String categoryName;

    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String description;
}
