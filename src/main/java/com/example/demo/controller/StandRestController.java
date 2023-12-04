package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.PostService;
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stand")
@RequiredArgsConstructor
public class StandRestController {
    private final PostService postService;
private final ResponseService responseService;
    @PostMapping("/response/{postId}")
    public HttpStatus respond(@PathVariable Long postId, @RequestBody MessageDto responseText) {
        return postService.processResponse(postId, responseText);
    }

    @PostMapping("/request_detail/message/{conversationId}")
    public HttpStatus customerRequestDetail(@PathVariable String conversationId, @RequestBody MessageDto responseText) {
        Long p = postService.getPostByConversationId(conversationId);
        if (p != null) {
            responseText.setViewer(conversationId);
            return postService.processResponse(p, responseText);
        }
        return HttpStatus.NOT_FOUND;
    }
    @PostMapping("/find_new/specialist_customer")
    public ResponseEntity<?> updateMessagesForSpecialist(@RequestBody MessageDto messageDto) {
        String conversationId = messageDto.getViewer();
        String localDateTime = messageDto.getResponse();
        List<ResponseDto> list = responseService.updateMessagesForSpecialist(localDateTime, conversationId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/find_new/customer_specialist")
    public ResponseEntity<?> updateMessagesForCustomer(@RequestBody MessageDto messageDto) {
        String conversationId = messageDto.getViewer();
        String localDateTime = messageDto.getResponse();
        List<ResponseDto> list = responseService.updateMessagesForCustomer(localDateTime, conversationId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}