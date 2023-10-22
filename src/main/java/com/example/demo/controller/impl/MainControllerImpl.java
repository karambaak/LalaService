package com.example.demo.controller.impl;

import com.example.demo.controller.MainController;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class MainControllerImpl implements MainController {
    private final CategoryService categoryService;

    @Override
    public String mainPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "main";
    }
}
