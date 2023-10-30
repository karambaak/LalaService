package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
@RequiredArgsConstructor
public class MessageRestController {
    private final MessageService messageService;
    @PostMapping("/new/{msgId}")
    public HttpStatus newMessage(@PathVariable String msgId, @RequestBody MessageDto responseText) {
       return messageService.saveMessage(msgId, responseText);
    }
    @PostMapping("/new")
    public HttpStatus newMsg(@RequestBody MessageDto messageDto) {
       return messageService.saveNewMessage(messageDto);
    }
}
