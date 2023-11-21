package com.example.demo.repository;

import com.example.demo.entities.SpecialistsAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialistsAuthoritiesRepository extends JpaRepository<SpecialistsAuthorities, Long> {
    Optional<SpecialistsAuthorities> findBySpecialistId(Long id);


}
