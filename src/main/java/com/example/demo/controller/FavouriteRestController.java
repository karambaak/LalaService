package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.FavouriteService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fav")
@RequiredArgsConstructor
public class FavouriteRestController {
    private final UserService userService;
    private final FavouriteService favouriteService;

    @PostMapping("/add/{specialistId}")
    public HttpStatus addFavourites(@PathVariable long specialistId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.getUserByPhone(auth.getName());
        favouriteService.saveFavourite(user.getId(),specialistId);
        return HttpStatus.OK;
    }
}
