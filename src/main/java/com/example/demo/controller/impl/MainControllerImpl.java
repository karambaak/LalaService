package com.example.demo.controller.impl;

import com.example.demo.controller.MainController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class MainControllerImpl implements MainController {

    @Override
    public String mainPage(Model model) {
        return "main";
    }
}
