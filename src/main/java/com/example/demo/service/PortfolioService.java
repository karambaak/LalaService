package com.example.demo.service;

import com.example.demo.dto.PortfolioDto;
import com.example.demo.entities.Portfolio;
import com.example.demo.repository.PortfolioRepository;
import com.example.demo.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class  PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final SpecialistRepository specialistRepository;

    public Page<PortfolioDto> getAllPortfolios(int start, int end) {
        Pageable pageable = PageRequest.of(start, end);
        Page<Portfolio> portfolios = portfolioRepository.findAll(pageable);
        Page<PortfolioDto> portfolioDtos = portfolios.map(portfolio -> {
            return PortfolioDto.builder()
                    .id(portfolio.getId())
                    .specialistId(portfolio.getSpecialist().getId())
                    .title(portfolio.getTitle())
                    .build();

        });
        return portfolioDtos;
    }
    

    public void savePortfolio(PortfolioDto portfolioDto) {
        portfolioRepository.save(Portfolio.builder()
                .specialist(specialistRepository.findById(
                                portfolioDto.getSpecialistId())
                        .orElseThrow(() -> new NoSuchElementException("Specialist not found")
                        )
                )
                .title(portfolioDto.getTitle())
                .build());
    }
}
