package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //       @ManyToOne(fetch = FetchType.EAGER)
//       @JoinColumn(name = "specialist_id")
//       private Specialist specialist;
//    private List<Photo> photos;
}
