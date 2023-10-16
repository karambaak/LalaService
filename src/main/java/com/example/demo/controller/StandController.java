package com.example.demo.controller;

import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stand")
@RequiredArgsConstructor
public class StandController {
    private final PostService postService;

    @GetMapping()
    public String getStand(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "stand/stand";
    }

    @PostMapping("/respond/{id}")
    public HttpStatus respond(@PathVariable Long postId, @RequestBody String responseText) {
        //Логика будет написана в следующем тикете
        System.out.println(responseText);
        return HttpStatus.OK;
    }
}
