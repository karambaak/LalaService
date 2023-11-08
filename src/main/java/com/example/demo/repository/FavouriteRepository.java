package com.example.demo.repository;

import com.example.demo.entities.Favourite;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    void deleteFavouriteByUserIdAndAndSpecialistId(long userId,long specialistId);
    List<Favourite> findFavouriteByUserId (long userId);
    boolean existsByUserIdAndSpecialistId(long userId, long specialistId);
    Optional<Favourite>findByUserAndSpecialist(User user, Specialist specialist);
}
