package com.example.demo.controller;

import com.example.demo.dto.RatingDto;
import com.example.demo.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingRestController {
    private final RatingService service;

    @PostMapping("/new/{specialistId}")
    public ResponseEntity<String> newRating(@PathVariable Long specialistId, @Valid @RequestBody RatingDto dto){
        dto.setSpecialistId(specialistId);
        return service.saveRating(dto);
    }
}
