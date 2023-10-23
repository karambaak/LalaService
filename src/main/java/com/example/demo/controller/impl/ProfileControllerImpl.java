package com.example.demo.controller.impl;

import com.example.demo.controller.ProfileController;
import com.example.demo.dto.UserDto;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProfileControllerImpl implements ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    @Override
    public String profile(Authentication auth, Model model) {
        User authUser = (User) auth.getPrincipal();
        UserDto currentUser = userService.getUserByAuthentication(auth);

        if (currentUser.getRole().equalsIgnoreCase("ROLE_SPECIALIST")){
            model.addAttribute("resumes", resumeService.getResumesByUserId(currentUser.getId()));
        }
        model.addAttribute("user", currentUser);
        return "users/profile";
    }
}
