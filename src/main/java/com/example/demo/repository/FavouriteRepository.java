package com.example.demo.repository;

import com.example.demo.entities.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    void deleteFavouriteByUserIdAndAndSpecialistId(long userId,long specialistId);
}
