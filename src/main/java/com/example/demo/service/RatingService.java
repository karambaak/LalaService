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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingService {
    private final RatingsRepository ratingsRepository;
    private final UserRepository userRepository;
    private final SpecialistRepository specialistRepository;
    private final SpecialistService specialistService;

    public ResponseEntity<String> saveRating(RatingDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        User currentUser = userRepository.findByPhoneNumber(userDetails.getUsername()).orElse(null);
        Specialist specialist = specialistRepository.findById(dto.getSpecialistId()).orElse(null);

        var maybeSpecialist = specialistRepository.findByUser_Id(currentUser.getId());

        if (isUserAlreadyLeaveRating(currentUser, specialist)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Вы можете оставить отзыв только один раз в течение 24 часов.");
        }

        if (maybeSpecialist.isPresent()) {
            if (maybeSpecialist.get().getId() == specialist.getId()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("специалист не может оставить отзыв себе");
            }
        }

        if (currentUser != null && specialist != null) {
            Ratings ratings = Ratings.builder()
                    .user(currentUser)
                    .specialist(specialist)
                    .rating(dto.getRatingValue())
                    .reviewText(dto.getReviewText())
                    .ratingDate(LocalDateTime.now())
                    .build();
            ratingsRepository.save(ratings);
            return ResponseEntity.ok("Отзыв сохранен");
        } else {
            log.warn("User or Specialist does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь или специалист не найден");
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


    public double getSpecialistRatingById(Long specialistId) {
        var specialist = specialistRepository.findById(specialistId);
        if (specialist.isEmpty()) return 0.0;
        if (specialistService.isFullAuthority(specialist.get())) {
            List<Ratings> ratings = ratingsRepository.getRatingsBySpecialistId(specialistId);

            double averageRating = ratings.stream()
                    .mapToInt(Ratings::getRating)
                    .average()
                    .orElse(Double.NaN);


            return Math.round(averageRating * 10.0) / 10.0;
        } else {
            return 0.0;
        }
    }

    public List<RatingDto> getRatingsBySpecialistId(Long specialistId) {
        return ratingsRepository.findBySpecialistId(specialistId).stream()
                .map(rating -> RatingDto.builder()
                        .id(rating.getId())
                        .specialistId(rating.getSpecialist().getId())
                        .ratingValue(rating.getRating())
                        .reviewText(rating.getReviewText())
                        .ratingDate(rating.getRatingDate())
                        .build())
                .collect(Collectors.toList());
    }

    private boolean isUserAlreadyLeaveRating(User currentUser, Specialist specialist) {
        var maybeRating = ratingsRepository.findFirstByUser_IdAndSpecialist_IdOrderByRatingDateDesc(currentUser.getId(), specialist.getId());

        if (maybeRating.isPresent()) {
            LocalDateTime ratingDateTime = maybeRating.get().getRatingDate();
            LocalDateTime currentDateTime = LocalDateTime.now();

            long hoursDifference = ChronoUnit.HOURS.between(ratingDateTime, currentDateTime);

            return hoursDifference < 24;
        } else {
            return false;
        }
    }

    public ResponseEntity<String> deleteRatingById(Long ratingId, Authentication auth) {
        var rating = ratingsRepository.findById(ratingId);
        var userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User currentUser = userRepository.findByPhoneNumber(userDetails.getUsername()).orElseThrow(() -> new NoSuchElementException("User not found"));

        if (rating.isPresent()) {
            User ownerOfTheRating = rating.get().getUser();

            if (ownerOfTheRating.getId().equals(currentUser.getId())) {
                ratingsRepository.deleteById(ratingId);
                return ResponseEntity.status(HttpStatus.OK).body(String.format("Рейтинг с ID %s успешно удалено", ratingId));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка при удалени рейтинга");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Рейтинг с таким ID %s не найден", ratingId));
        }

    }

}
