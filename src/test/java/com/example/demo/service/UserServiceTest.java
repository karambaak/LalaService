package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private AuthUserDetailsService service;

    @Test
    public void testRegisterNewUser() {
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("1234567890");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setUserName("TestUser");
        userDto.setRole("role_customer");
        Role role = new Role(1L, "ROLE_CUSTOMER", new ArrayList<>());

        when(userRepository.findByPhoneNumber(userDto.getPhoneNumber())).thenReturn(Optional.empty());
        when(userService.defineUserRole(userDto.getRole())).thenReturn(role);
        when(userService.defineUserType(userDto.getRole())).thenReturn("Customer");
        assertDoesNotThrow(() -> userService.register(userDto));

        // Проверяем, что метод findByPhoneNumber был вызван с правильным номером телефона
        verify(userRepository, times(1)).findByPhoneNumber("1234567890");

        // Проверяем, что метод save был вызван с объектом User
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        Assertions.assertEquals("1234567890", savedUser.getPhoneNumber());
        Assertions.assertEquals("test@example.com", savedUser.getEmail());

    }

    @Test
    public void testRegisterExistingUser() {
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("1234567890");

        // Mocking repository behavior when findByPhoneNumber is called
        when(userRepository.findByPhoneNumber(userDto.getPhoneNumber())).thenReturn(Optional.of(new User()));

        // Testing the case where the user already exists
        assertThrows(IllegalArgumentException.class, () -> userService.register(userDto));
    }
}
