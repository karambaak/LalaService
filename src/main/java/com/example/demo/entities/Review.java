package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;
}
