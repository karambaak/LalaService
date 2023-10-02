package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {
    private Long id;
    private String userName;
    private String role;
    private String phoneNumber;
    private String email;
    private String password;
}
