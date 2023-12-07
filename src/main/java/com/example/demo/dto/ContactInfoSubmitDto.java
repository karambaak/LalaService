package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactInfoSubmitDto {
    private String contactType;
    private String contactValue;
}
