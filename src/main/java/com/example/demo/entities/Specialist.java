package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "specialists")
public class Specialist {

    @Id
    private Long id;

    @Column(name = "user_id")
    private User user;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "photo")
    private Photo photo;

    @ManyToOne
    @JoinColumn(name = "tariff_id", referencedColumnName = "id")
    private Tariff tariff;


}
