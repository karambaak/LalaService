package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Role role = null;
        if (userDto.getRole().equalsIgnoreCase("role_specialist")) {
            role = roleRepository.findByRole("ROLE_SPECIALIST");
        }
        if (userDto.getRole().equalsIgnoreCase("role_customer")) {
            role = roleRepository.findByRole("ROLE_CUSTOMER");
        }
        if (role == null) {
            log.warn("Invalid role name: {}", userDto.getRole());
            throw new IllegalArgumentException("Invalid role name: " + userDto.getRole());
        }
        var user = User.builder()
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .userName(userDto.getUserName())
                .role(role)
                .enabled(Boolean.TRUE)
                .build();
        repository.save(user);
    }

    public List<String> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Role r :
                roles) {
            list.add(r.getRole());
        }
        repository.saveAndFlush(user);
    }

}
