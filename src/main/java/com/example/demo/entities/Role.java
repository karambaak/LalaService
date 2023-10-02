package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

<<<<<<<<< Temporary merge branch 1
=========
import java.util.List;

>>>>>>>>> Temporary merge branch 2
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "role")
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> user;
}
