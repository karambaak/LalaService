package com.example.demo.controller;

import com.example.demo.dto.NotificationDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface NotificationRestController {

    @GetMapping("/notifications")
    List<NotificationDto> getNotificationsByUserAuth(Authentication auth);
}
