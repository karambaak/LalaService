package com.example.demo.controller.impl;

import com.example.demo.controller.ProfileController;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.SpecialistRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.RatingService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.utils.QRCodeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProfileControllerImpl implements ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final QRCodeServiceImpl qrCodeService;
    private final RatingService ratingService;
    private final PostService postService;
    private final SpecialistRepository specialistRepository;


    @Override
    public String profile(Authentication auth, Model model) {
        UserDto currentUser = userService.getUserByAuthentication(auth);
        Long specialistId = null;

        if (currentUser.getRole().equalsIgnoreCase("ROLE_SPECIALIST")){
            specialistId = specialistRepository.findByUser_Id(currentUser.getId()).get().getId();
            model.addAttribute("resumes", resumeService.getResumesByUserId(currentUser.getId()));
            model.addAttribute("rating", ratingService.getSpecialistRatingById(currentUser.getId()));
            String qrCodeText = "http://localhost:8089//favorites/add/" + specialistId;
            ByteArrayOutputStream qrCodeStream = QRCode.from(qrCodeText).stream();
            byte[] qrCodeBytes = qrCodeStream.toByteArray();
            String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeBytes);
            model.addAttribute("qrCodeBase64", qrCodeBase64);
        }
        if (currentUser.getRole().equalsIgnoreCase("ROLE_CUSTOMER")) {
            model.addAttribute("stands", postService.getCustomerPostDtos(currentUser.getId()));
        }

        model.addAttribute("user", currentUser);

        return "users/profile";
    }

}
