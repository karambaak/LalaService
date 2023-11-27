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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/auth")
public interface AuthController {

    @GetMapping("/register")
    public String register(Model model) throws IOException;

    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult) throws IOException;

    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "false", required = false) Boolean error, Model model);

    @GetMapping("/oauth_2")
    public String pickRole(Model model);

    @PostMapping("/oauth_2")
    public String pickRole(HttpServletRequest request, Authentication auth);
}