package com.example.demo.controller.impl;

import com.example.demo.controller.ProfileController;
import com.example.demo.dto.UserDto;
import com.example.demo.service.RatingService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.utils.QRCodeServiceImpl;
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
    private final QRCodeServiceImpl qrCodeService;
    private final RatingService ratingService;


    @Override
    public String profile(Authentication auth, Model model) {
        User authUser = (User) auth.getPrincipal();
        UserDto currentUser = userService.getUserByAuthentication(auth);

        if (currentUser.getRole().equalsIgnoreCase("ROLE_SPECIALIST")){
            model.addAttribute("resumes", resumeService.getResumesByUserId(currentUser.getId()));
            model.addAttribute("rating", ratingService.getSpecialistRatingById(currentUser.getId()));
        }
        model.addAttribute("user", currentUser);
        model.addAttribute("qrCode", qrCodeService.generateQRCode("/favorites/add/" + currentUser.getPhoneNumber(), 500, 500));
        return "users/profile";
    }
}
