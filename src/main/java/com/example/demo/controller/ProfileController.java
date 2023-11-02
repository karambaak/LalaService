package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public interface ProfileController {

    @GetMapping("/profile")
    String profile(Authentication auth, Model model);

}
