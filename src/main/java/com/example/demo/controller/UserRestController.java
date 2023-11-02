package com.example.demo.controller;

import com.example.demo.dto.CountryDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.service.GeolocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final GeolocationService geolocationService;

    @GetMapping("/auth/geolocation")
    public List<CountryDto> getGeolocations() {
        return geolocationService.getCountries();
    }
    @PostMapping("/auth/user/register")
    public HttpStatus register(MessageDto messageDto) {
        System.out.println(messageDto.getResponse());
        System.out.println(messageDto.getViewer());
//        System.out.println(messageDto.getPhone());
        return HttpStatus.OK;
    }
}
