package com.example.demo.repository;

import com.example.demo.entities.Portfolio;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface PortfolioRepository extends JpaRepositoryImplementation<Portfolio,Integer> {
}
