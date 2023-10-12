package com.example.demo.service;

import com.example.demo.dto.SpecialistDto;
import com.example.demo.entities.Specialist;
import com.example.demo.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecialistService {
    private final SpecialistRepository repository;

    public List<SpecialistDto> searchSpecialistByName(String name){
        log.info("New query for searching: {}", name);
        List<Specialist> specialists = repository.searchSpecialistByCompanyNameContainingIgnoreCase(name);

        log.info("Searching results: {}", specialists.toString());
        return specialists.stream().map(this::makeDto).collect(Collectors.toList());
    }

    private SpecialistDto makeDto(Specialist specialist){
        return SpecialistDto.builder()
                .id(specialist.getId())
                .companyName(specialist.getCompanyName())
                .build();
    }
}
