package com.example.demo.controller;

import com.example.demo.dto.RatingDto;
import com.example.demo.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {
    private final RatingService service;

    @GetMapping("/new/{specialistId}")
    public String newRating(Model model, @PathVariable Long specialistId){
        model.addAttribute("specialistId", specialistId);
        return "ratings/rating";
    }

    @PostMapping("/new/{specialistId}")
    public String newRating(@PathVariable Long specialistId, RatingDto dto){
        dto.setSpecialistId(specialistId);
        service.saveRating(dto);
        return "redirect:/";
    }

    @GetMapping("/{specialistId}/reviews")
    public String readReviewsBySpecialistId(@PathVariable Long specialistId, Model model){
        model.addAttribute("reviews", service.getRatingsBySpecialistId(specialistId));
        return "ratings/view";
    }
}
