package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "update_counts")
public class UpdateCounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long userId;
    private Integer count;

    private Timestamp updateTime;

}
