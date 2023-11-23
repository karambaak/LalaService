package com.example.demo.service;

import com.example.demo.dto.TariffDto;
import com.example.demo.entities.SpecialistsAuthorities;
import com.example.demo.entities.SubscriptionsOnTariffs;
import com.example.demo.entities.Tariff;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionsOnTariffsService {
    private final SubscriptionsOnTariffsRepository subscriptionsOnTariffsRepository;
    private final SpecialistsAuthoritiesRepository specialistsAuthoritiesRepository;
    private final AuthorityRepository authorityRepository;
    private final SpecialistRepository specialistRepository;
    private final UserRepository userRepository;
    private final TariffRepository tariffRepository;

    @Scheduled(cron = "@daily")
    public void tariffChecking(){
        List<Long> specialistsIds = subscriptionsOnTariffsRepository.findAll().stream()
                .filter(e -> e.getEndPeriodTime().isBefore(LocalDateTime.now()))
                .map(e -> e.getUser().getSpecialist().getId())
                .toList();
        for (Long id : specialistsIds){
            specialistsAuthoritiesRepository.findBySpecialistId(id).orElseThrow(() -> new NoSuchElementException("Authority not found")).setAuthority(authorityRepository.findByAuthorityName("LIMITED"));
            log.info("Authority changed in specialist id: {}", id);
        }
    }


    public void addPaymentOnTariffByUserAuth(TariffDto tariffDto, User user) {
       com.example.demo.entities.User userByAuth = userRepository.findByPhoneNumber(user.getUsername())
               .orElseThrow(() -> new NoSuchElementException("User not found"));
       Tariff tariff = tariffRepository.findById(tariffDto.getId())
               .orElseThrow(() -> new NoSuchElementException("Tariff not found"));
       LocalDateTime dateOfEnd = LocalDateTime.now().plusDays(tariff.getDayAmount());
       subscriptionsOnTariffsRepository.save(SubscriptionsOnTariffs.builder()
                       .user(userByAuth)
                       .tariff(Tariff.builder()
                               .id(tariffDto.getId())
                               .tariffName(tariffDto.getTariffName())
                               .cost(tariffDto.getCost())
                               .availability(tariffDto.getAvailability())
                               .build())
                       .startPeriodTime(LocalDateTime.now())
                       .endPeriodTime(dateOfEnd)
               .build());
       Optional<SpecialistsAuthorities> specialistAuthority = specialistsAuthoritiesRepository.findBySpecialistId(userByAuth.getSpecialist().getId());
        specialistAuthority.ifPresent(specialistsAuthorities -> specialistsAuthoritiesRepository.save(SpecialistsAuthorities.builder()
                .specialist(specialistsAuthorities.getSpecialist())
                .authority(authorityRepository.findByAuthorityName("FULL"))
                .dateStart(LocalDateTime.now())
                .dateEnd(dateOfEnd)
                .id(specialistsAuthorities.getId())
                .build()));
    }
}
