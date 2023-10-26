package com.example.demo.repository;

import com.example.demo.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Ratings, Long> {
    Ratings findByUserIdAndSpecialistId(long userId,long specialistId);

    List<Ratings> getRatingsBySpecialistId(Long specialistId);
}
