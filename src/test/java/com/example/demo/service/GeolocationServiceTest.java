package com.example.demo.service;

import com.example.demo.dto.CountryDto;
import io.cucumber.java.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GeolocationServiceTest {
    @Autowired
    private GeolocationService geolocationService;
    @Mock
    CountryDto countryDto;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCountriesMethod() {
        List<CountryDto> actualList = geolocationService.getCountries();
        assertEquals(1, actualList.size());
        assertEquals("Кыргызстан", actualList.get(0).getCountry());
        assertEquals(31, actualList.get(0).getCities().size());
    }

}
