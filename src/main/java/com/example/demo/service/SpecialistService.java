package com.example.demo.service;

import com.example.demo.dto.SpecialistDto;
import com.example.demo.entities.Specialist;
import com.example.demo.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialistService {
    private final SpecialistRepository repository;

    public List<SpecialistDto> searchSpecialistByName(String name){
        List<Specialist> specialists = repository.searchByCompanyName(name);

        return specialists.stream().map(this::makeDto).collect(Collectors.toList());
    }

    private SpecialistDto makeDto(Specialist specialist){
        return SpecialistDto.builder()
                .id(specialist.getId())
                .companyName(specialist.getCompanyName())
                .build();
    }
}
