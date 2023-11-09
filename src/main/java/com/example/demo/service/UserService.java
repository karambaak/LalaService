package com.example.demo.service;

import com.example.demo.dto.FindUserNameDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.ViewerDto;
import com.example.demo.entities.*;
import com.example.demo.enums.UserType;
import com.example.demo.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    public static final String SPECIALIST = "specialist";
    private final UserRepository repository;
    private final SpecialistRepository specialistRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthUserDetailsService service;
    private final GeolocationRepository geolocationRepository;

    private final UpdateCountsRepository updateCountsRepository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void register(UserDto userDto) {
        var u = repository.findByPhoneNumber(userDto.getPhoneNumber());
        var geolocation = geolocationRepository.findByCountryAndCity(userDto.getCountry(), userDto.getCity());
        Geolocation geo = null;
        if (geolocation.isPresent()) geo = geolocation.get();
        if (u.isEmpty()) {
            Role role = defineUserRole(userDto.getRole());
            String userType = defineUserType(userDto.getRole());
            var user = User.builder()
                    .phoneNumber(userDto.getPhoneNumber())
                    .email(userDto.getEmail())
                    .password(encoder.encode(userDto.getPassword()))
                    .userName(userDto.getUserName())
                    .userType(userType)
                    .role(role)
                    .geolocation(geo)
                    .enabled(Boolean.TRUE)
                    .registrationDate(LocalDateTime.now())
                    .build();
            repository.saveAndFlush(user);
            if (userType.equalsIgnoreCase(SPECIALIST)) {
                var newUser = repository.findByPhoneNumber(userDto.getPhoneNumber());
                newUser.ifPresent(value -> specialistRepository.save(Specialist.builder()
                        .user(value)
                        .build()));
            }

        } else {
            log.warn("User already exists: {}", userDto.getPhoneNumber());
            throw new IllegalArgumentException("User already exists");
        }
    }

    private Role defineUserRole(String userRole) {
        Role role = null;
        if (userRole.equalsIgnoreCase("role_specialist")) {
            role = roleRepository.findByRole("ROLE_SPECIALIST");
        }
        if (userRole.equalsIgnoreCase("role_customer")) {
            role = roleRepository.findByRole("ROLE_CUSTOMER");
        }
        if (role == null) {
            log.warn("Invalid role name: {}", userRole);
            throw new IllegalArgumentException("Invalid role name: " + userRole);
        }
        return role;
    }

    public User getUserFromSecurityContextHolder() {
        String username = getUsernameFromSecurityContextHolder();
        if (username.isEmpty()) return null;
        return findUserByUsername(username);
    }

    public String defineUserType(String userRole) {
        if (userRole == null) {
            log.warn("Passed a null value for user role");
            throw new IllegalArgumentException("User role cannot be null");
        }
        int underscoreIndex = userRole.indexOf('_');
        String type = userRole.substring(underscoreIndex + 1);
        UserType userType = null;
        for (UserType t : UserType.values()) {
            if (t.name().equalsIgnoreCase(type)) {
                userType = t;
            }
        }
        if (userType == null) {
            log.warn("Unknown user role was passed as argument");
            throw new IllegalArgumentException("User role has unknown value");
        }
        return userType.name();
    }

    public List<String> getRoles() {
        return roleRepository.findAll().stream()
                .map(Role::getRole)
                .toList();
    }


    public void updateUser(HttpServletRequest request, Authentication auth) {
        String selectedRole = request.getParameter("role");
        Role role = defineUserRole(selectedRole);
        String userType = defineUserType(selectedRole);
        var userAuth = (OAuth2User) auth.getPrincipal();
        String email = userAuth.getAttribute("email");
        var user = repository.getByEmail(email);
        var geolocation = geolocationRepository.findByCountryAndCity(request.getParameter("country"), request.getParameter("city"));
        Geolocation geo = null;
        if (geolocation.isPresent()) geo = geolocation.get();
        if (user.isPresent()) {
            User u = user.get();
            u.setRole(role);
            u.setGeolocation(geo);
            u.setUserType(userType);
            repository.saveAndFlush(u);

            if (role.getRole().equalsIgnoreCase("ROLE_SPECIALIST")) {
                specialistRepository.save(Specialist.builder()
                        .user(u)
                        .build());
            }
            service.loadUserByUsernameEmail(user.get().getEmail());
        }

    }

    public UserDto getUserByAuthentication(Authentication auth) {
        var principal = auth.getPrincipal();
        if (principal instanceof CustomOAuth2User c) {
            return makeUserDto(repository.findByEmail((c.getEmail())).orElseThrow(() -> new NoSuchElementException("Auth is null, user email not found")));
        } else {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            if (isValidEmail(user.getUsername())) {
                return makeUserDto(repository.findByEmail(user.getUsername()).orElseThrow(() -> new NoSuchElementException("Auth is null, user email not found")));
            } else {
                return makeUserDto(repository.findByPhoneNumber(user.getUsername()).orElseThrow(() -> new NoSuchElementException("Auth is null, user phone number not found")));
            }
        }
    }

    public UserDto getUserByPhone(String phone) {
        return makeUserDto(repository.findByPhoneNumber(phone).orElseThrow(() -> new NoSuchElementException("Auth is null, user not found")));
    }

    private UserDto makeUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .role(user.getRole().getRole())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .country(user.getGeolocation().getCountry())
                .city(user.getGeolocation().getCity())
                .build();
    }

    public String getUsernameFromSecurityContextHolder() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) {
                return null;
            }
            var principal = auth.getPrincipal();
            if (principal instanceof CustomOAuth2User c) {
                return c.getEmail();
            } else {
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;

                return user.getUsername();
            }
        } catch (Exception e) {

            return null;
        }
    }

    public ViewerDto defineViewer() {
        String username = getUsernameFromSecurityContextHolder();

        if (username == null) return null;

        User user = findUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            if (user.getUserType().equalsIgnoreCase(SPECIALIST)) {
                var specialist = specialistRepository.findByUser(user);
                if (specialist.isPresent()) {

                    return ViewerDto.builder()
                            .userId(user.getId())
                            .specialistId(specialist.get().getId())
                            .userType(user.getUserType())
                            .build();
                }

            } else if (user.getUserType().equalsIgnoreCase("customer")) {
                return ViewerDto.builder()
                        .userId(user.getId())
                        .specialistId(null)
                        .userType(user.getUserType())
                        .build();
            } else {
                return null;
            }
        }
        return null;
    }

    private User findUserByUsername(String username) {
        if (isValidEmail(username)) {
            return repository.findByEmail(username).orElse(null);
        } else {
            return repository.findByPhoneNumber(username).orElse(null);
        }
    }

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public UserDto getSpecialistUserById(Long specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new NoSuchElementException("Specialist not found"));
        var user = repository.findById(specialist.getUser().getId());
        return user.map(this::makeUserDto).orElse(null);
    }

    public List<FindUserNameDto> getUserNameList() {
        List<User> users = repository.findAll();
        List<FindUserNameDto> list = new ArrayList<>();
        for (User u : users) {
            StringBuilder s = new StringBuilder().append(u.getUserName());
            if (u.getUserType().equalsIgnoreCase(SPECIALIST)) {
                var specialist = specialistRepository.findByUser(u);
                if (specialist.isPresent() && specialist.get().getCompanyName() != null) {
                    s.append("/").append(specialist.get().getCompanyName());
                }
            }
            list.add(FindUserNameDto.builder()
                    .id(u.getId())
                    .userName(s.toString())
                    .build());
        }
        return list;
    }

    public void editProfile(User user, long geolocationId) {
        Geolocation geolocation = geolocationRepository.findById(geolocationId).orElseThrow(() -> new NoSuchElementException("No such location!"));
        if (!updateCountsRepository.existsByUserId(user.getId())) {
            updateCountsRepository.save(UpdateCounts.builder()
                    .userId(user.getId())
                    .updateTime(new Timestamp(System.currentTimeMillis()))
                    .count(1)
                    .build());
            repository.updateUserInformationWithGeolocation(user.getId(), user.getUserName(), user.getPhoneNumber(),
                    user.getEmail(), geolocation);

        } else {
            int currentCount = updateCountsRepository.findByUserId(user.getId()).
                    orElseThrow(() -> new NoSuchElementException("No such count!")).getCount();
            if (currentCount < 5) {
                currentCount++;
                updateCountsRepository.setCountForUser(user.getId(), currentCount);
                repository.updateUserInformationWithGeolocation(user.getId(), user.getUserName(), user.getPhoneNumber(),
                        user.getEmail(), geolocation);
            }
        }
    }


}