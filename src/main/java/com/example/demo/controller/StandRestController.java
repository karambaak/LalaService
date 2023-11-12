package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
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
    public HttpStatus respond(@PathVariable Long postId, @RequestBody MessageDto responseText) {
        return postService.processResponse(postId, responseText);
    }

    @PostMapping("/request_detail/{conversationId}")
    public HttpStatus customerRequestDetail(@PathVariable String conversationId, @RequestBody MessageDto responseText) {
        Long p = postService.getPostByConversationId(conversationId);
        if (p != null) {
            responseText.setViewer(conversationId);
            return postService.processResponse(p, responseText);
        }
        return HttpStatus.NOT_FOUND;
    }
}