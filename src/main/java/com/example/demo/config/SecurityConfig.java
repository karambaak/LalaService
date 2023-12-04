package com.example.demo.config;

import com.example.demo.entities.CustomOAuth2User;
import com.example.demo.service.AuthUserDetailsService;
import com.example.demo.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final AuthUserDetailsService userDetailsService;
    private static final String LOGIN = "/auth/login";
    private static final String SPECIALIST = "ROLE_SPECIALIST";
    private static final String CUSTOMER = "ROLE_CUSTOMER";
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage(LOGIN)
                        .loginProcessingUrl(LOGIN)
                        .defaultSuccessUrl("/profile")
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/portfolio/delete/**").hasAuthority(SPECIALIST)
                                .requestMatchers("/tariff/**").hasAuthority(SPECIALIST)
                                .requestMatchers("/portfolio/new").hasAuthority(SPECIALIST)
                                .requestMatchers("/api/rating/**").hasAuthority(CUSTOMER)
                                .requestMatchers("/rating/**").fullyAuthenticated()
                                .requestMatchers("/specialist/**").fullyAuthenticated()
                                .requestMatchers("/stand/").fullyAuthenticated()
                                .requestMatchers("/stand/respond/**").hasAuthority(SPECIALIST)
                                .requestMatchers("/stand/request/**").hasAuthority(CUSTOMER)
                                .requestMatchers("/stand/new/**").hasAuthority(CUSTOMER)
                                .requestMatchers("/stand/show/**").fullyAuthenticated()
                                .requestMatchers("/stand/request_detail/**").fullyAuthenticated()
                                .requestMatchers("/stand/response/**").fullyAuthenticated()
//                        .requestMatchers("/stand/request_detail/**").fullyAuthenticated()
                                .requestMatchers("/stand/find_new/**").fullyAuthenticated()
                                .requestMatchers("/stand/delete/**").hasAuthority(CUSTOMER)
                                .requestMatchers("/profile/**").fullyAuthenticated()
                                .requestMatchers("/fav/**").fullyAuthenticated()
                                .requestMatchers("/msg/**").fullyAuthenticated()
                                .requestMatchers("/resume/create").hasAuthority(SPECIALIST)
                                .requestMatchers("/resume/**").fullyAuthenticated()
                                .requestMatchers("/stand/select").hasAuthority(CUSTOMER)
                                .requestMatchers("/api/search/specialist/**").permitAll()
                                .requestMatchers("/api/save-theme-preference").fullyAuthenticated()
                                .requestMatchers("/api/get-theme").fullyAuthenticated()
                                .requestMatchers("/api/notifications").fullyAuthenticated()
                                .requestMatchers("/favourites/**").fullyAuthenticated()
                                .requestMatchers("/contact/**").fullyAuthenticated()

                        .anyRequest().permitAll()
                )
                .rememberMe(customizer -> customizer
                        .key("secret")
                        .tokenValiditySeconds(60))
                .oauth2Login(oauth -> oauth
                        .loginPage(LOGIN)
                        .userInfoEndpoint(config -> config
                                .userService(customOAuth2UserService))
                        .successHandler((request, response, authentication) -> {
                            CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
                            if(userDetailsService.processOAuthPostLogin(user, request)) {
                                response.sendRedirect("/auth/oauth_2");
                            } else {
                                response.sendRedirect("/");
                            }
                        }));
        return http.build();
    }
}