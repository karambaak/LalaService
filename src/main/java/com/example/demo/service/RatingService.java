package com.example.demo.service;

import com.example.demo.dto.RatingDto;
import com.example.demo.entities.Ratings;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.repository.RatingsRepository;
import com.example.demo.repository.SpecialistRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingService {
    private final RatingsRepository ratingsRepository;
    private final UserRepository userRepository;
    private final SpecialistRepository specialistRepository;

    public void saveRating(RatingDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername()).orElse(null);
        Specialist specialist = specialistRepository.findById(dto.getSpecialistId()).orElse(null);
        if (user != null && specialist != null) {
            Ratings ratings = Ratings.builder()
                    .user(user)
                    .specialist(specialist)
                    .rating(dto.getRatingValue())
                    .reviewText(dto.getReviewText())
                    .build();
            ratingsRepository.save(ratings);
        } else {
            log.warn("User or Specialist does not exist");
            throw new IllegalArgumentException("User or Specialist does not exist");
        }
    }

    public void updateRating(long userId, long specialistId, int rating, String reviewText) {
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
                        .reviewText(reviewText)
                        .build();
                ratingsRepository.save(newRating);
            }

        } else {
            log.warn("User or Specialist does not exist");
            throw new IllegalArgumentException("User or Specialist does not exist");
        }
    }


    public double getSpecialistRatingById(Long specialistId){
        List<Ratings> ratings = ratingsRepository.getRatingsBySpecialistId(specialistId);

        double averageRating = ratings.stream()
                .mapToInt(Ratings::getRating)
                .average()
                .orElse(Double.NaN);


        return Math.round(averageRating*10.0)/10.0;
    }
}
