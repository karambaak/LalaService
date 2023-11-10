package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/find_new")
    public ResponseEntity<?> findNewMessages (@RequestBody MessageDto messageDto) {
        String conversationId = messageDto.getViewer();
        String localDateTime = messageDto.getResponse();
        List<ResponseDto> list = messageService.findNewMessages(localDateTime, conversationId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
