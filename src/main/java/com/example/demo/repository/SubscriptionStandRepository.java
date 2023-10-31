package com.example.demo.repository;

import com.example.demo.entities.SubscriptionStand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionStandRepository extends JpaRepository<SubscriptionStand, Integer> {
    List<SubscriptionStand> findAllBySpecialistId(Long specialistId);
    List<SubscriptionStand> findByCategory_Id(Long id);
}
