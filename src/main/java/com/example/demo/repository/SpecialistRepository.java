package com.example.demo.repository;

import com.example.demo.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {

    List<Specialist> searchSpecialistByCompanyNameContainingIgnoreCase(String companyName);
}
