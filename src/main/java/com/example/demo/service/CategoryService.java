package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryDto> getAllCategories(){
        return repository.findAll().stream()
                .map(e -> CategoryDto.builder()
                        .id(e.getId())
                        .categoryName(e.getCategoryName())
                        .description(e.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
