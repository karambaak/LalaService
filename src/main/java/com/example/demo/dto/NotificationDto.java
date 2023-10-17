package com.example.demo.dto;

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
    private LocalDateTime notificationDate;
}
