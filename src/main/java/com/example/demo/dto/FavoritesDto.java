package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FavoritesDto {
    private Long userId;
    private String companyName;
    private String city;
    private List<ResumeDto> resumes;
    private Long specilaitsId;
}
