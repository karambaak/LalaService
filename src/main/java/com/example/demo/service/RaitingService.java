package com.example.demo.service;

import com.example.demo.entities.Ratings;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.repository.RatingsRepository;
import com.example.demo.repository.SpecialistRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RaitingService {
    private final RatingsRepository ratingsRepository;
    private final UserRepository userRepository;
    private final SpecialistRepository specialistRepository;

    public void saveRating(long userId, long specialistId, int rating) {
        User user = userRepository.findById(userId).orElse(null);
        Specialist specialist = specialistRepository.findById(specialistId).orElse(null);
        if (user != null && specialist != null) {
            Ratings ratings = Ratings.builder()
                    .user(user)
                    .specialist(specialist)
                    .rating(rating)
                    .build();
            ratingsRepository.save(ratings);
        } else {
            log.warn("User or Specialist does not exist");
            throw new IllegalArgumentException("User or Specialist does not exist");
        }
    }

    public void updateRating(long userId, long specialistId, int rating) {
        User user = userRepository.findById(userId).orElse(null);
        Specialist specialist = specialistRepository.findById(specialistId).orElse(null);
        if (user != null && specialist != null) {
            Ratings existingRating = ratingsRepository.findByUserIdAndSpecialistId(userId, specialistId);

            if (existingRating != null) {
                existingRating.setRating(rating);
                ratingsRepository.save(existingRating);
            } else {
                Ratings newRating = Ratings.builder()
                        .user(user)
                        .specialist(specialist)
                        .rating(rating)
                        .build();
                ratingsRepository.save(newRating);
            }

        } else {
            log.warn("User or Specialist does not exist");
            throw new IllegalArgumentException("User or Specialist does not exist");
        }
    }


}
