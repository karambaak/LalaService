package com.example.demo.controller.impl;

import com.example.demo.controller.MainRestController;
import com.example.demo.dto.SpecialistDto;
import com.example.demo.service.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MainRestControllerImpl implements MainRestController {
    private final SpecialistService service;

    @Override
    public List<SpecialistDto> searchSpecialistByName(String name) {
        return service.searchSpecialistByName(name);
    }
}
