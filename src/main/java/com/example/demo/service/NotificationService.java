package com.example.demo.service;

import com.example.demo.dto.NotificationDto;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public List<NotificationDto> getNotificationsByUserAuth(Authentication auth) {
        User user = (User) auth.getPrincipal();
        String phoneNumber = user.getUsername();
        Long userId = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new NoSuchElementException("User not found")).getId();
        return notificationRepository.findAllByUser_Id(userId).stream()
                .map(e -> NotificationDto.builder()
                        .id(e.getId())
                        .userId(e.getUser().getId())
                        .notificationText(e.getNotificationText())
                        .notificationDate(e.getNotificationDate())
                        .build())
                .collect(Collectors.toList());


    }
}
