package com.example.demo.repository;

import com.example.demo.entities.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {
}
