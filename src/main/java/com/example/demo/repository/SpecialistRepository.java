package com.example.demo.repository;

import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {

    List<Specialist> searchSpecialistByCompanyNameContainingIgnoreCase(String companyName);

    Optional<Specialist> findByUser_Id(Long userId);

    Optional<Specialist> findByUser(User user);

    Optional<Specialist> findByUserId(long userId);

}
