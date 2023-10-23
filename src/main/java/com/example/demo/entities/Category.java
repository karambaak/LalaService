package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoryName;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Post> posts;
    @OneToMany(mappedBy = "category")
    private List<SubscriptionStand> subscriptions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Resume> resumes;

}
