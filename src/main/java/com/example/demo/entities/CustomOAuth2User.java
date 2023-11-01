package com.example.demo.entities;

import com.example.demo.repository.UserRepository;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private OAuth2User oAuth2User;
    private final UserRepository userRepository;


    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var user = userRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isPresent()) {
            Role role = user.get().getRole();
            return List.of(new SimpleGrantedAuthority(role.getRole()));
        } else {
            return oAuth2User.getAuthorities();
        }
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    public String getPhoto() {
        return oAuth2User.getAttribute("picture");
    }
}
