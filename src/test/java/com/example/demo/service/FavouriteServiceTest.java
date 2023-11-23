package com.example.demo.service;

import com.example.demo.dto.FavoritesDto;
import com.example.demo.entities.Favourite;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.repository.SpecialistRepository;
import com.example.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FavouriteServiceTest {
    @InjectMocks
    private FavouriteService favouriteService;

    @Mock
    private FavouriteRepository favouriteRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SpecialistRepository specialistRepository;
    @Mock
    private ResumeService resumeService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_a_list_when_user_exists() {
        List<FavoritesDto> list = favouriteService.getFavourites(1L);
        assertNotNull(list);
    }

    @Test
    public void should_get_an_emtpy_list_when_user_does_not_exist() {
        List<FavoritesDto> list = favouriteService.getFavourites(100L);
        assertTrue(list.isEmpty());
    }

    @Test
    public void should_save_favourite_when_not_exists() {
        Long userId = 1L;
        Long specialistId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(specialistRepository.findById(specialistId)).thenReturn(Optional.of(new Specialist()));
        when(favouriteRepository.findByUserAndSpecialist(any(), any())).thenReturn(Optional.empty());

        favouriteService.saveFavourite(userId, specialistId);

        verify(favouriteRepository, times(1)).save(any());
    }

    @Test
    public void should_not_save_favourite_when_already_exists() {
        Long userId = 1L;
        Long specialistId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(specialistRepository.findById(specialistId)).thenReturn(Optional.of(new Specialist()));
        when(favouriteRepository.findByUserAndSpecialist(any(), any()))
                .thenReturn(Optional.of(new Favourite()));

        favouriteService.saveFavourite(userId, specialistId);

        verify(favouriteRepository, never()).save(any());
    }

    @Test
    public void should_throw_exception_when_user_not_found() {
        Long userId = 1L;
        Long specialistId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> favouriteService.saveFavourite(userId, specialistId));
    }

    @Test
    public void should_throw_exception_when_specialist_not_found() {
        Long userId = 1L;
        Long specialistId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(specialistRepository.findById(specialistId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> favouriteService.saveFavourite(userId, specialistId));
    }
}
