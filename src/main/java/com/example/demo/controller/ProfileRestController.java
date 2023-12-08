package com.example.demo.controller;

import com.example.demo.dto.ContactInfoSubmitDto;
import com.example.demo.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileRestController {
    private final ContactsService contactsService;

    @PostMapping("/submit-contact-info")
    public HttpStatus submitBusinessCard(@RequestBody List<ContactInfoSubmitDto> contactInfoSubmitDto) {
        contactsService.saveNewContactInfo(contactInfoSubmitDto);
        return HttpStatus.OK;
    }
}
