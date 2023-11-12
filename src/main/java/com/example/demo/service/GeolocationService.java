package com.example.demo.service;

import com.example.demo.dto.CountryDto;
import com.example.demo.entities.Geolocation;
import com.example.demo.repository.GeolocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeolocationService {
    private final GeolocationRepository geolocationRepository;

    public List<CountryDto> getCountries() {
        List<CountryDto> list = new ArrayList<>();
        List<Geolocation> geolocations = geolocationRepository.findAll();
        Set<String> countries = geolocations.stream()
                .map(Geolocation::getCountry)
                .collect(Collectors.toSet());
        for (String s : countries) {
            List<String> cities = new ArrayList<>();
            for (Geolocation g : geolocations) {
                if (g.getCountry().equalsIgnoreCase(s)) cities.add(g.getCity());
            }
            list.add(CountryDto.builder()
                    .country(s)
                    .cities(cities)
                    .build());
        }
        return list;
    }

}
