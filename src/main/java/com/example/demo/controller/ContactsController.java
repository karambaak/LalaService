package com.example.demo.controller;

import com.example.demo.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactsController {
    private final ContactsService contactsService;
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("contacts", contactsService.getAll());
        model.addAttribute("searchResults", contactsService.getSearchResultItems());
        return "specialist/contacts";
    }
}
