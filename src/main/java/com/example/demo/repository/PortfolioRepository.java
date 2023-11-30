package com.example.demo.repository;

import com.example.demo.entities.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepositoryImplementation<Portfolio,Integer> {
    Page<Portfolio> findAll(Pageable pageable);
    Boolean findPortfolioByIdAndAndSpecialistId(long portfolioId,long specialistId);

    Optional<Portfolio> findPortfolioById(long id);
    List<Portfolio> findAllBySpecialist_Id(Long id);
    void deleteById(long id);
}
