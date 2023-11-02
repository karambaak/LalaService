package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class ContactsDto {
    private String category;
    private List<SpecialistShortInfoDto> specialists;
}
