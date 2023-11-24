package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "specialist_id", referencedColumnName = "id")
    private Specialist specialist;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    //состоит из двух цифр и тире (PostId-SpecialistId).
    // Используется для поиска запросов, на которые откликнулся специалист.
    // Используется, чтобы выдавать список сообщений под каждым запросом.
    private String conversationId;

    private String response;

    @Column(name="date_time")
    private LocalDateTime dateTime;
}
