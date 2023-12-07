package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/super")
@RequiredArgsConstructor
public class SuperUserRestController {
    private final UserService userService;

    @GetMapping("/block/{userId}")
    public ResponseEntity<?> blockUserById(@PathVariable Long userId) {
        return userService.blockOrUnBlockUserByUserId(userId);
    }

}
