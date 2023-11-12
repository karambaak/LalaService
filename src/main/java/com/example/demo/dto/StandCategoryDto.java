package com.example.demo.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StandCategoryDto {
    private String category;
    private List<PostDto> postDtoList;
}
