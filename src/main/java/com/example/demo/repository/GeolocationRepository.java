package com.example.demo.repository;

import com.example.demo.entities.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {
    Optional<Geolocation> findByCountryAndCity(String country, String city);
}
