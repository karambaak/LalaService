package com.example.demo.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@Component
public class CountryCode {
    private Integer code;
    private String icon;
    private Integer countOfNumber;
    public static List<CountryCode> readCountryCodesFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = CountryCode.class.getResourceAsStream("/static/phone_number_codes.json");
        if (inputStream == null) {
            throw new IOException("File phone_number_codes.json not found");
        }
        List<CountryCode> countryCodes = objectMapper.readValue(inputStream,
                new TypeReference<List<CountryCode>>() {
                });
        return countryCodes;
    }
}
