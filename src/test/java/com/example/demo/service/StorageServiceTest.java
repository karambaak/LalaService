package com.example.demo.service;

import com.example.demo.entities.Photo;
import com.example.demo.repository.PhotosRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StorageServiceTest {
    @InjectMocks
    private StorageService photoService;

    @Mock
    private PhotosRepository photoRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_return_urls_for_existing_photos() {
        List<Photo> mockPhotos = Arrays.asList(
                new Photo(),
                new Photo(),
                new Photo()
        );
        when(photoRepository.findAll()).thenReturn(mockPhotos);
        List<String> result = photoService.getAllPhotos();
        assertEquals(3, result.size());
    }

    @Test
    public void should_return_empty_list_for_no_photos() {
        when(photoRepository.findAll()).thenReturn(Collections.emptyList());
        List<String> result = photoService.getAllPhotos();
        assertTrue(result.isEmpty());
    }

    @Test
    public void should_handle_null_repository_result() {
        when(photoRepository.findAll()).thenReturn(Collections.emptyList());
        List<String> result = photoService.getAllPhotos();
        assertTrue(result.isEmpty());
    }
}
