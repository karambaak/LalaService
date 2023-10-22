package com.example.demo.repository;

import com.example.demo.entities.Post;
import com.example.demo.entities.Response;
import com.example.demo.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findAllByPostId(Long postId);

    List<Response> findByPostAndSpecialist(Post post, Specialist specialist);
}
