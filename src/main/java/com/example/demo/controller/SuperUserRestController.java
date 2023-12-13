package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/super")
@RequiredArgsConstructor
public class SuperUserRestController {
    private final UserService userService;

    @PostMapping("/block/{userId}")
    public ResponseEntity<?> blockUserById(@PathVariable Long userId) {
        return userService.blockOrUnBlockUserByUserId(userId);
    }

    @GetMapping("/users")
    public ResponseEntity< List<UserDto>> getAllUsers() {
        Map<UserDto, List<Long>> users = userService.getAllUsersAndResumesIdsForAdmin();
        List<UserDto> userDtoList = new ArrayList<>(users.keySet());
        userDtoList.sort(Comparator.comparing(UserDto::getId));
        return ResponseEntity.ok(userDtoList);
    }

}
