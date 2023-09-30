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
@Table(name = "ratings")
public class Ratings {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialist_id", referencedColumnName = "id")
    private Specialist specialist;

    @Column(name = "user_id")
    private User user;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "raiting_date")
    private LocalDateTime localDateTime;


}
