package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "subscriptions_on_tariffs")
public class SubscriptionsOnTariffs {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tariff_id", referencedColumnName = "id")
    private Tariff tariff;

    @Column(name = "start_period_time")
    private LocalDateTime startPeriodTime;

    @Column(name = "end_period_time")
    private LocalDateTime endPeriodTime;
}
