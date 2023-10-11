package com.example.demo.repository;

import com.example.demo.entities.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume,Integer> {

    Page<Resume> findAll(Pageable pageable);

}
