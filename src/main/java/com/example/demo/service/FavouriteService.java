package com.example.demo.service;

import com.example.demo.dto.FavoritesDto;
import com.example.demo.entities.Favourite;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.repository.SpecialistRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final SpecialistRepository specialistRepository;
    private final ResumeService resumeService;

    public void saveFavourite(long userId, long specialistId) {
        User user = userRepository.findById(userId).orElse(null);
        Specialist specialist = specialistRepository.findById(specialistId).orElse(null);

        if (user != null && specialist != null) {
            if (!favouriteRepository.existsByUserIdAndSpecialistId(userId, specialistId)) {
                Favourite favourite = Favourite.builder()
                        .user(user)
                        .specialist(specialist)
                        .build();
                favouriteRepository.save(favourite);
            }
        } else {
            log.warn("User or Specialist does not exist");
            throw new IllegalArgumentException("User or Specialist does not exist");
        }
    }

    @Transactional
    public void deleteFavourite(long userId, long specialistId) {

        User user = userRepository.findById(userId).orElse(null);
        Specialist specialist = specialistRepository.findById(specialistId).orElse(null);
        if (user != null && specialist != null) {
            favouriteRepository.deleteFavouriteByUserIdAndAndSpecialistId(userId, specialistId);
        } else {
            log.warn("User or Specialist does not exist");
            throw new IllegalArgumentException("User or Specialist does not exist");
        }
    }

    public List<FavoritesDto> getFavouritesByUserId(long userId) {
        List<Favourite> favourites = favouriteRepository.findFavouriteByUserId(userId);
        return favourites.stream().map(
                e -> FavoritesDto.builder()
                        .userId(userId)
                        .companyName(e.getSpecialist().getCompanyName())
                        .city(e.getSpecialist().getGeolocation().getCity())
                        .resumes(resumeService.getResumesByUserId(userId))
                        .specilaitsId(e.getSpecialist().getId())
                        .build()
        ).collect(Collectors.toList());
    }


}
