package com.example.demo.controller.impl;

import com.example.demo.controller.AuthController;
import com.example.demo.dto.UserDto;
import com.example.demo.service.GeolocationService;
import com.example.demo.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final UserService userService;
    private final GeolocationService geolocationService;

    @GetMapping("/register")
    public String register(Model model) throws IOException {
        model.addAttribute("countryCodes", userService.getCountryCodes());
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("roles", userService.getRoles());
        model.addAttribute("countries", geolocationService.getCountries());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()) {
            userService.register(userDto);
            return "redirect:/auth/login";
        }
        return "redirect:/auth/register";
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(defaultValue = "false", required = false) Boolean error,
            Model model
    ) {
        if (error.equals(Boolean.TRUE)) {
            model.addAttribute("error", "Неправильный номер телефона или пароль");
        }
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


    @GetMapping("/forgot_password")
    public String resetPassword() {
        return "auth/forgot_password";
    }

    @PostMapping("/forgot_password")
    public String forgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswdLink(request);
            String message = "Мы отправили вам ссылку для сброса пароля на вашу почту. Пожалуйста, проверьте.";
            model.addAttribute("message", message);
        } catch (UsernameNotFoundException | UnsupportedEncodingException ex) {
            model.addAttribute("error","Не удалось найти пользователя с такой электронной почтой");
        } catch (MessagingException ex) {
            String message = "Ошибка при отправке электронной почты";
            model.addAttribute("error", message);
        }
        return "auth/forgot_password";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(
            @RequestParam(name = "token") String token,
            Model model
    ) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException ex) {
            String message = "Неверный токен.";
            model.addAttribute("error", message);
        }
        return "auth/reset_password";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            UserDto user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            String successMessage = "Вы успешно изменили пароль.";
            model.addAttribute("message", successMessage);
        } catch (UsernameNotFoundException ex) {
            String message = "Вы успешно изменили пароль.";
            model.addAttribute("message", message);
        }
        return "message";
    }
}