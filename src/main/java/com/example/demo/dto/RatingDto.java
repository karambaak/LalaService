package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RatingDto {
    Long id;
    Long specialistId;

    @NotNull
    @Max(value = 5, message = "Максимальный рейтинг 5")
    @Min(value = 1, message = "Должно быть не меньше 1")
    Integer ratingValue;

    LocalDateTime ratingDate;
    String reviewText;
}
