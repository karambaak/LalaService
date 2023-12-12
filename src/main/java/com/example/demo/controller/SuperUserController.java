package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/super")
@RequiredArgsConstructor
public class SuperUserController {
    private final UserService userService;

    @GetMapping("/profiles")
    public String getUsersProfileForSuperUser(Model model) {
        model.addAttribute("usersAndResumesId", userService.getAllUsersAndResumesIdsForAdmin());
        model.addAttribute("usersAndPostsId", userService.getAllUsersAndPostsIdsForAdmin());
        return "users/managment";
    }
}
