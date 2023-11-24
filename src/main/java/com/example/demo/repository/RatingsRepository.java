package com.example.demo.repository;

import com.example.demo.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingsRepository extends JpaRepository<Ratings, Long> {
    Ratings findByUserIdAndSpecialistId(long userId,long specialistId);

    List<Ratings> getRatingsBySpecialistId(Long specialistId);

    Optional<Ratings> findFirstByUser_IdAndSpecialist_IdOrderByRatingDateDesc(Long userId, Long specialistId);

    List<Ratings> findBySpecialistId(Long specialistId);
}
