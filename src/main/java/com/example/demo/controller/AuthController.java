package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public interface AuthController {

    @GetMapping("/register")
    public String register(Model model);

    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult);

    @GetMapping("/login")
    public String login();

    @GetMapping("/oauth_2")
    public String pickRole(Model model);

    @PostMapping("/oauth_2")
    public String pickRole(HttpServletRequest request, Authentication auth);
}