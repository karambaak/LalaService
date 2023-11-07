package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public interface ProfileController {

    @GetMapping("/profile")
    String profile(Authentication auth, Model model);

    @GetMapping("/profile/{userId}")
    String getProfileByUserId(@PathVariable Long userId, Model model, Authentication auth);

}
