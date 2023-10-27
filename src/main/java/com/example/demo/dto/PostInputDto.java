package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PostInputDto {
    private String title;
    private String description;
    private LocalDateTime workRequiredTime;
    private Long categoryId;
}
