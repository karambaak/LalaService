package com.example.demo.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class CountryCode {
    private Integer code;
    private String icon;
    private Integer countOfNumber;

    public static List<CountryCode> readCountryCodesFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CountryCode> countryCodes = objectMapper.readValue(
                new File("phone_number_codes.json"),
                new TypeReference<>() {
                });
        return countryCodes;
    }
}
