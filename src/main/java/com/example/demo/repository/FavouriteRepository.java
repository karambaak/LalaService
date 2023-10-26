package com.example.demo.repository;

import com.example.demo.entities.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    void deleteFavouriteByUserIdAndAndSpecialistId(long userId,long specialistId);
    List<Favourite> findFavouriteByUserId (long userId);
    boolean existsByUserIdAndSpecialistId(long userId, long specialistId);
}
