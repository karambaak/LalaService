package com.example.demo.controller.impl;


import com.example.demo.controller.ResumeController;
import com.example.demo.dto.ResumeDto;
import com.example.demo.errors.exceptions.OnlyOneResumeInSameCategoryException;
import com.example.demo.service.CategoryService;
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
    private final CategoryService categoryService;


    @Override
    public String getResumesByCategory(Long categoryId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            model.addAttribute("noUser","noUser");
        }
        model.addAttribute("resumes", service.getResumesByCategory(categoryId));
        return "resumes/resumes";
    }

    @Override
    public String createResume(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "resumes/create";
    }

    @Override
    public String createResume(ResumeDto dto) throws OnlyOneResumeInSameCategoryException {
        service.saveResume(dto);
        return "redirect:/profile";
    }
}
