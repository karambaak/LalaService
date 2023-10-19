package com.example.demo.dto;

import com.example.demo.entities.Category;
import com.example.demo.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PostDto {
    private Long id;
    private String userPhoto;
    private String userName;
    private String category;

    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String description;

    @Min(value = 0, message = "Response number must be a non-negative integer")
    private int responseNumber;

    private LocalDateTime workRequiredTime;
    private LocalDateTime publishedDate;
}
