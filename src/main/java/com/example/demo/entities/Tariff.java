package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    private Long id;

    @Column(name = "tariff_name")
    private String tariffName;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "availability")
    private Boolean availability;
}
