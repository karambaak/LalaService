package com.example.demo.dto;

import lombok.Data;

@Data
public class RatingDto {
    Long id;
    Long specialistId;
    Integer ratingValue;
    String reviewText;
}
