package com.example.demo.controller;

import com.example.demo.dto.ViewerDto;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stand")
@RequiredArgsConstructor
public class StandController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping()
    public String getStand(Model model) {
        ViewerDto v = userService.defineViewer();
        model.addAttribute("viewer", v);

        if (v != null) {
            if (v.getSpecialistId() != null) {
                model.addAttribute("myPosts", postService.getMySubscriptions(v));
                model.addAttribute("posts", postService.getOtherPosts(v));
                model.addAttribute("myResponses", postService.getSpecialistResponses(v));
            } else {
                model.addAttribute("myRequests", postService.getCustomerPosts(v));
                model.addAttribute("posts", postService.getAll());
            }
        } else {
            model.addAttribute("posts", postService.getAll());
        }
        return "stand/stand";
    }

    @GetMapping("/respond/{postId}")
    public String respondPage(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.getPostDto(postId));
        ViewerDto v = userService.defineViewer();
        model.addAttribute("viewer", v);
        if (v.getSpecialistId() != null) {
            model.addAttribute("pastMessages", postService.getSpecialistConversation(postId, v));
        }
        return "stand/respond";
    }

    @GetMapping("/request/{postId}")
    public String requestPage(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.getPostDto(postId));
        ViewerDto v = userService.defineViewer();
        model.addAttribute("viewer", v);
        if(v.getSpecialistId() == null) {
            model.addAttribute("conversations", postService.getCustomerConversations(postId));
        }
        return "stand/request";
    }

}
