package com.example.demo.repository;

import com.example.demo.entities.Category;
import com.example.demo.entities.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume,Integer> {

    Page<Resume> findAll(Pageable pageable);
    List<Resume> findAllBySpecialist_Id(Long id);
    Boolean findResumeBySpecialistIdAndId(long specialistId,long resumeId);

    List<Resume> findByCategoryId(Long categoryId);

    List<Resume> findAllByCategory(Category category);
}
