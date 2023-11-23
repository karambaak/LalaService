package com.example.demo.repository;

import com.example.demo.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByAuthorityName(String authorityName);
}
