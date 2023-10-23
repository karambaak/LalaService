package com.example.demo.controller;

import com.example.demo.dto.ViewerDto;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stand")
@RequiredArgsConstructor
public class StandController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping()
    public String getStand(Model model) {
        ViewerDto v = userService.defineSpecialist();
        model.addAttribute("specialist", v);
        if (v != null) {
            model.addAttribute("myPosts", postService.getMySubscriptions(v));
            model.addAttribute("posts", postService.getOtherPosts(v));
        } else {
            model.addAttribute("posts", postService.getAll());
        }
        return "stand/stand";
    }

    @GetMapping("/respond/{postId}")
    public String respondPage(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.getPostDto(postId));
        return "stand/respond";
    }


}
