package com.example.demo.repository;

import com.example.demo.entities.SpecialistsAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistsAuthoritiesRepository extends JpaRepository<SpecialistsAuthorities, Long> {
    SpecialistsAuthorities findBySpecialistId(Long id);
}
