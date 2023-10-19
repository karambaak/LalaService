package com.example.demo.service;

import com.example.demo.dto.SpecialistDto;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecialistService {
    private final SpecialistRepository repository;

    public List<SpecialistDto> searchSpecialistByName(String name){
        List<Specialist> specialists = repository.searchSpecialistByCompanyNameContainingIgnoreCase(name);
        log.info("Specialist:  {}", specialists.toString());
        return specialists.stream().map(this::makeDto).collect(Collectors.toList());
    }

    public SpecialistDto getSpecialistByAuthentication(Authentication auth){
        User user = (User) auth.getPrincipal();
        return makeDto(repository.findByUser(user).orElseThrow(() -> new NoSuchElementException("Specialist not found")));
    }

    private SpecialistDto makeDto(Specialist specialist){
        return SpecialistDto.builder()
                .id(specialist.getId())
                .companyName(specialist.getCompanyName())
                .build();
    }
}
