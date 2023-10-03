package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(userEntity.getPhoneNumber(), userEntity.getPassword(), userEntity.getAuthorities());
    }


    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(
//                user.getPhoneNumber(),
//                user.getPassword(),
//                user.isEnabled(),
//                true,
//                true,
//                true,
//                getAuthorities(user.getRole())
//        );
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
//        return getGrantedAuthorities(getPrivileges(role));
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String privilege : privileges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }
//
//    private List<String> getPrivileges(Collection<Role> roles) {
//        List<String> privileges = new ArrayList<>();
//
//        privileges.add(role.getRole());
//
//        privileges.add("FULL");
//
//        // ROLE_USER, FULL, READ_ONLY
//        return privileges;
//    }


}