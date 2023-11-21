package com.example.demo.service;

import com.example.demo.repository.SubscriptionsOnTariffsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionsOnTariffsService {
    private final SubscriptionsOnTariffsRepository subscriptionsOnTariffsRepository;


    @Scheduled(cron = "@daily")
    public void tariffChecking(){

    }
}
