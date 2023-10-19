package com.example.demo.controller.impl;


import com.example.demo.controller.ResumeController;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class ResumeControllerImpl implements ResumeController {
    private final ResumeService service;

    @Override
    public String getResumesByCategory(Integer categoryId, Model model) {
        return null;
    }
}
