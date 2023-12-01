package com.example.demo.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@Component
public class CountryCode {
    private Integer code;
    private String icon;
    private Integer countOfNumber;
    public static List<CountryCode> readCountryCodesFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CountryCode> countryCodes = objectMapper.readValue(
                new File("src/main/resources/static/phone_number_codes.json"),
                new TypeReference<>() {
                });
        return countryCodes;
    }
}
