package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@GetMapping("/{id}")
public String getConversation(@PathVariable String id, Model model) {
        model.addAttribute("messages", messageService.viewMessages(id));
        model.addAttribute("msgId", id);
        return "messages/conversation";
}

    @GetMapping("/new")
    public String newMessage(@PathVariable String msgId, Model model) {
        return "messages/new";
    }

}
