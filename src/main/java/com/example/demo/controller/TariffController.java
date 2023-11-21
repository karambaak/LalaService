package com.example.demo.controller;

import com.example.demo.dto.TariffDto;
import com.example.demo.service.SubscriptionsOnTariffsService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tariff")
@RequiredArgsConstructor
public class TariffController {
    private final SubscriptionsOnTariffsService subscriptionsOnTariffsService;

    @GetMapping
    public String getTariffs(){
        return "tariffs/tariff";
    }

    @PostMapping
    public void saveTariff(TariffDto tariffDto, Authentication auth){
        User user = (User) auth.getPrincipal();
        subscriptionsOnTariffsService.addPaymentOnTariffByUserAuth(tariffDto, user);
    }
}
