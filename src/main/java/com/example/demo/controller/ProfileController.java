package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public interface ProfileController {

    @GetMapping("/profile")
    String profile(Authentication auth, Model model);

    @GetMapping("/profile/{userId}")
    String getProfileByUserId(@PathVariable Long userId, Model model, Authentication auth);

    @GetMapping("/profile/edit")
    String getProfileEdit(Model model, Authentication auth);

    @PostMapping("/profile/edit")
    String updateProfile(Model model, Authentication auth,
                         @RequestParam(name = "username") String userName,
                         @RequestParam(name = "city") Long geolocationId,
                         @RequestParam(name = "email") String email

    );

}
