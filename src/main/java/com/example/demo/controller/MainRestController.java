package com.example.demo.controller;

import com.example.demo.dto.SpecialistDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface MainRestController {

    @GetMapping("/search/specialist/{name}")
    List<SpecialistDto> searchSpecialistByName(@PathVariable String name);

    @PostMapping("/save-theme-preference")
    public HttpStatus saveTheme(@RequestBody String theme);

    @GetMapping("/get-theme")
    public String getTheme();
}
