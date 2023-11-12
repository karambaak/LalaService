package com.example.demo.repository;

import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
    Optional<Post> findByTitleAndWorkRequiredTime(String title, LocalDateTime date);
}
