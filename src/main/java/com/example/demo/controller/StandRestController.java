package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stand")
@RequiredArgsConstructor
public class StandRestController {
    private final PostService postService;

    @PostMapping("/response/{postId}")
    public HttpStatus respond(@PathVariable Long postId, @RequestBody ResponseDto responseText) {
        return postService.processResponse(postId, responseText);
    }
}
