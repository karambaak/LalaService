package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        var user = User.builder()
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .userName(userDto.getUserName())
                .enabled(Boolean.TRUE)
                .build();
        if (userDto.getRole().equalsIgnoreCase("specialist")) {
            user.setRole(roleRepository.findByRole("ROLE_SPECIALIST"));
        }
        if (userDto.getRole().equalsIgnoreCase("customer")) {
            user.setRole(roleRepository.findByRole("ROLE_CUSTOMER"));
        }
        repository.saveAndFlush(user);
    }

}
