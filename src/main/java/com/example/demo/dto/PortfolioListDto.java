package com.example.demo.dto;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PortfolioListDto {
    private Long id;
    private String title;
    private Timestamp timeOfPortfolio;
    private String coverPhotoLink;
}
