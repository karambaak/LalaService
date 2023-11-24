package com.example.demo.service;

import com.example.demo.dto.RatingDto;
import com.example.demo.entities.Ratings;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.repository.RatingsRepository;
import com.example.demo.repository.SpecialistRepository;
import com.example.demo.repository.UserRepository;
import io.cucumber.java.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RatingServiceTest {

    @InjectMocks
    private RatingService service;

    @Mock
    private RatingsRepository repository;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private SpecialistRepository specialistRepositoryMock;

    @Mock
    Authentication authenticationMock;

    @Mock
    org.springframework.security.core.userdetails.User userDetailsMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void itShouldReturnRatingsBySpecialistId() {
        Specialist validSpecialist = Specialist.builder().id(1L).build();
        Specialist inValidSpecialist = Specialist.builder().id(2L).build();
        User reviewer = User.builder().id(2L).build();

        List<Ratings> ratings = Arrays.asList(
                Ratings.builder().specialist(validSpecialist).user(reviewer).build(),
                Ratings.builder().specialist(validSpecialist).user(reviewer).build(),
                Ratings.builder().specialist(validSpecialist).user(reviewer).build()
        );

        when(repository.findBySpecialistId(validSpecialist.getId())).thenReturn(ratings);
        List<RatingDto> result = service.getRatingsBySpecialistId(validSpecialist.getId());
        List<RatingDto> invalidResult = service.getRatingsBySpecialistId(inValidSpecialist.getId());

        assertNotNull(result, "Result should not be null");
        assertEquals(3, result.size());
        assertEquals(0, invalidResult.size());

        assertEquals(ratings.get(0).getId(), result.get(0).getId(), "Unexpected rating ID");
        assertEquals(ratings.get(0).getSpecialist().getId(), result.get(0).getSpecialistId(), "Unexpected specialist ID");
        assertEquals(ratings.get(2).getRating(), result.get(2).getRatingValue(), "Unexpected rating value");
        assertEquals(ratings.get(1).getReviewText(), result.get(1).getReviewText(), "Unexpected review text");
    }

    @Test
    void itShouldSaveRating() {
        RatingDto ratingDto = RatingDto.builder().ratingValue(2).reviewText("Hello").specialistId(1L).build();
        User currentUser = User.builder().id(2L).build();
        Specialist specialist = Specialist.builder().id(1L).build();

        when(authenticationMock.getPrincipal()).thenReturn(userDetailsMock);
        when(userDetailsMock.getUsername()).thenReturn("user123");

        SecurityContextHolder.getContext().setAuthentication(authenticationMock);

        when(userRepositoryMock.findByPhoneNumber("user123")).thenReturn(Optional.of(currentUser));
        when(specialistRepositoryMock.findById(1L)).thenReturn(Optional.of(specialist));
        when(specialistRepositoryMock.findByUser_Id(1L)).thenReturn(Optional.of(specialist));

        ResponseEntity<String> result = service.saveRating(ratingDto);

        assertEquals(HttpStatus.OK, result.getStatusCode(), "Expected OK status");
        assertEquals("Отзыв сохранен", result.getBody(), "Unexpected response body");

        verify(repository, times(1)).save(any(Ratings.class));
    }


}
