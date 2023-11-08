package com.example.demo.controller;

import com.example.demo.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/portfolio")
@RestController
@RequiredArgsConstructor
public class PortfolioRestController {
    private final PortfolioService portfolioService;

    @PostMapping("/new")
    public HttpStatus newPortfolio(@RequestParam("title") String title, @RequestParam("photos") MultipartFile[] photos) {
        portfolioService.createNew(title, photos);
        return HttpStatus.CREATED;
    }

}
