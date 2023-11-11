package com.example.demo.service;

import com.example.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class UserTypeTest {
    UserRepository userRepository = mock(UserRepository.class);
    SpecialistRepository specialistRepository = mock(SpecialistRepository.class);
    RoleRepository roleRepository = mock(RoleRepository.class);

    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    AuthUserDetailsService authUserDetailsService = mock(AuthUserDetailsService.class);
    GeolocationRepository geolocationRepository = mock(GeolocationRepository.class);
    UpdateCountsRepository updateCountsRepository = mock(UpdateCountsRepository.class);

    UserService userService = new UserService(userRepository, specialistRepository, roleRepository, passwordEncoder, authUserDetailsService, geolocationRepository, updateCountsRepository);

    @Test
    void insertRoleSpecialistShouldReturnUserTypeSpecialist() {
        String insert = "ROLE_SPECIALIST";
        String userType = userService.defineUserType(insert);
        assertEquals("specialist", userType.toLowerCase());
    }

    @Test
    void insertRoleCustomerShouldReturnUserTypeCustomer() {
        String insert = "ROLE_CUSTOMER";
        String userType = userService.defineUserType(insert);
        assertEquals("customer", userType.toLowerCase());
    }

    @Test
    void insertNullReturnIllegalArgumentException() {
        String insert = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.defineUserType(insert);
        });
    }

    @Test
    void insertUserReturnIllegalArgumentException() {
        String insert = "User";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.defineUserType(insert);
        });
    }

}