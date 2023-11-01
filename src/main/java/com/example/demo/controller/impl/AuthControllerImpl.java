package com.example.demo.controller.impl;

import com.example.demo.controller.AuthController;
import com.example.demo.dto.UserDto;
import com.example.demo.service.GeolocationService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final UserService userService;
    private final GeolocationService geolocationService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("roles", userService.getRoles());
        model.addAttribute("countries", geolocationService.getCountries());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.register(userDto);
            return "redirect:/auth/login";
        }
        return "auth/register";
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/oauth_2")
    public String pickRole(Model model) {
        model.addAttribute("roles", userService.getRoles());
        model.addAttribute("countries", geolocationService.getCountries());
        return "auth/google_user_role";
    }

    @PostMapping("/oauth_2")
    public String pickRole(HttpServletRequest request, Authentication auth) {
        userService.updateUser(request, auth);
        return "redirect:/";
    }
}