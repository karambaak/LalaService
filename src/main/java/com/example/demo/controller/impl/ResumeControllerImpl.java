package com.example.demo.controller.impl;


import com.example.demo.controller.ResumeController;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class ResumeControllerImpl implements ResumeController {
    private final ResumeService service;


    @Override
    public String getResumesByCategory(Long categoryId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            model.addAttribute("noUser","noUser");
        }
        model.addAttribute("resumes", service.getResumesByCategory(categoryId));
        return "resumes/resumes";
    }
}
