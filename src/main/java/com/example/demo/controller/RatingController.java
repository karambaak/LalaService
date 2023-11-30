package com.example.demo.controller;

import com.example.demo.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {
    private final RatingService service;

    @GetMapping("/{specialistId}/reviews")
    public String readReviewsBySpecialistId(@PathVariable Long specialistId, Model model){
        model.addAttribute("reviews", service.getRatingsBySpecialistId(specialistId));
        return "ratings/view";
    }
}
