package com.example.demo.dto;

import com.example.demo.entities.Category;
import com.example.demo.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PostDto {
    private Long id;
    private String userName;
    private String category;
    private String title;
    private String description;
    private LocalDateTime workRequiredTime;
    private LocalDateTime publishedDate;
}
