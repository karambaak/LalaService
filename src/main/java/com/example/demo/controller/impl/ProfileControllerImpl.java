package com.example.demo.controller.impl;

import com.example.demo.controller.ProfileController;
import com.example.demo.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProfileControllerImpl implements ProfileController {
    @Override
    public String profile(Authentication auth) {
        User user = (User) auth.getPrincipal();
        log.info("User role: {}", user.getRole().getRole());
        return "users/profile";
    }
}
