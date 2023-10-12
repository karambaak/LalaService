package com.example.demo.controller;

import com.example.demo.dto.SpecialistDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface MainRestController {

    @GetMapping("/search/specialist/{name}")
    public List<SpecialistDto> searchSpecialistByName(@PathVariable String name);
}
