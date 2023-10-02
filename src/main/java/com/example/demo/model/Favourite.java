package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "favourites")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "specialist_id")
//    private Specialist specialist;

    //    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
