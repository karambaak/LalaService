package com.example.demo.dto;

import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NotificationDto {
    private Long id;
    private Long userId;
    private String notificationText;

    @Past(message = "Time must be in the past or current")
    private LocalDateTime notificationDate;
}
