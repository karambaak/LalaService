package com.example.demo.service;

import com.example.demo.entities.CustomOAuth2User;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
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


    public UserDetails loadUserByUsernameEmail(String username) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(username)
                .orElseThrow(() -> {
                    log.warn("User not found: {}", username);
                    return new UsernameNotFoundException("User not found");
                });

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                getAuthorities(user.getRole())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return getGrantedAuthorities(getPrivileges(role));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private List<String> getPrivileges(Role role) {
        List<String> privileges = new ArrayList<>();
        if (role != null) {
            privileges.add(role.getRole());
        } else {
            privileges.add("FULL");
        }

        // ROLE_USER, FULL, READ_ONLY
        return privileges;
    }


    public boolean processOAuthPostLogin(CustomOAuth2User user, HttpServletRequest request) {
        String username = user.getEmail();
        var existUser = userRepository.getByEmail(username);

        if (existUser.isEmpty()) {
            var u = User.builder()
                    .email(username)
                    .userName(user.getName())
                    .password("qwerty")
                    .photo(user.getPhoto())
                    .enabled(Boolean.TRUE)
                    .registrationDate(LocalDateTime.now())
                    .build();
            userRepository.saveAndFlush(u);

            return true;
        }
        WebAuthenticationDetails details = new WebAuthenticationDetails(request);
        UserDetails userDetails = loadUserByUsernameEmail(username);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        ((AbstractAuthenticationToken) auth).setDetails(details);

        return false;
    }
}