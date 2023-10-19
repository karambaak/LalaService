package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {
    private Long id;

    @NotBlank(message = "User name is required")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$",
            message = "Username must be 3-20 characters long and can only contain letters, numbers, hyphens, and underscores.")
    private String userName;


    private String role;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    @NotBlank(message = "Phone number  is required")
    private String phoneNumber;

    @Email(message = "Invalid email!")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Password should contain at least one uppercase letter, one number")
    private String password;
}
