package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resume")
public interface ResumeController {

    @GetMapping("/category/{categoryId}")
    String getResumesByCategory(@PathVariable Integer categoryId, Model model);
}