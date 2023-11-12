package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "specialists")
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Favourite> favourites;

    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;

    @ManyToOne
    @JoinColumn(name = "tariff_id", referencedColumnName = "id")
    private Tariff tariff;

    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contacts> contacts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialist")
    private List<Resume> resumes;

    @OneToMany(mappedBy = "specialist")
    private List<SubscriptionStand> subscriptions;

    @OneToMany(mappedBy = "specialist")
    private List<Response> responses;

    @OneToMany(mappedBy = "specialist")
    private List<Ratings> ratings;
}
