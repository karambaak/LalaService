package com.example.demo.controller;

import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/msg")
@RequiredArgsConstructor
public class MessageNotificationController {
    private final MessageService messageService;
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("messages", messageService.getAll());
        model.addAttribute("notifications", messageService.getAllNotifications());
        return "messages/message";
    }


    @GetMapping("/new")
    public String newMessage(Model model) {
        return "messages/new";
    }
}
