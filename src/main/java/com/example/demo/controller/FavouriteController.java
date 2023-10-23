package com.example.demo.controller;

import com.example.demo.dto.FavoritesDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.FavouriteService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/favourites")
@RequiredArgsConstructor
public class FavouriteController {
    private final FavouriteService favouriteService;
    private final UserService userService;

    @GetMapping
    public String getFavourites(Authentication auth, Model model) {
        User authUser = (User) auth.getPrincipal();
        UserDto user=userService.getUserByPhone(authUser.getUsername());
        List<FavoritesDto> favoritesDtoList = favouriteService.getFavouritesByUserId(user.getId());
        model.addAttribute("favourites", favoritesDtoList);
        return "favorites/favorites";

    }

}
