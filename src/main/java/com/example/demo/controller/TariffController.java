package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tariff")
@RequiredArgsConstructor
public class TariffController {

    @GetMapping
    public String getTariffs(){
        return "tariffs/tariff";
    }
}
