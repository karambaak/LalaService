package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PhotoDto {
    private Long id;
    private Long portfolioId;
    private String photoLink;

}
