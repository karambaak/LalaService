package com.example.demo.controller.impl;

import com.example.demo.controller.NotificationRestController;
import com.example.demo.dto.NotificationDto;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class NotificationRestControllerImpl implements NotificationRestController {

    private final NotificationService notificationService;

    @Override
    public List<NotificationDto> getNotificationsByUserAuth(Authentication auth) {
        return notificationService.getNotificationsByUserAuth(auth);
    }
}
