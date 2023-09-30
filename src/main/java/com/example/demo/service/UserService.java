package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void register(UserDto userDto) {
        var u = repository.findByPhoneNumber(userDto.getPhoneNumber());
        if(u.isEmpty()) {
            var user = User.builder()
                    .phoneNumber(userDto.getPhoneNumber())
                    .email(userDto.getEmail())
                    .password(encoder.encode(userDto.getPassword()))
                    .userName(userDto.getUserName())
                    .roles(new HashSet<>())
                    .enabled(Boolean.TRUE)
                    .registrationDate(LocalDateTime.now())
                    .build();
            if (userDto.getRole() == null) {
                user.addRole(roleRepository.findByRole("ROLE_USER"));
            } else if (userDto.getRole().equalsIgnoreCase("specialist")) {
                user.addRole(roleRepository.findByRole("ROLE_SPECIALIST"));
            } else if (userDto.getRole().equalsIgnoreCase("customer")) {
                user.addRole(roleRepository.findByRole("ROLE_CUSTOMER"));
            } else {
                user.addRole(roleRepository.findByRole("ROLE_USER"));
            }
            repository.saveAndFlush(user);
        } else {
            log.warn("User already exists: {}", userDto.getUserName());
            throw new IllegalArgumentException("User already exists");
        }
    }

}
