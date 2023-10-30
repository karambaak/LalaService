package com.example.demo.service;

import com.example.demo.dto.FindUserNameDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.ViewerDto;
import com.example.demo.entities.Role;
import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.enums.UserType;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SpecialistRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final SpecialistRepository specialistRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthUserDetailsService service;
    public static final String SPECIALIST = "specialist";

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void register(UserDto userDto) {
        var u = repository.findByPhoneNumber(userDto.getPhoneNumber());
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
        if (user.isPresent()) {
            User u = user.get();
            u.setRole(role);
            u.setUserType(userType);
            repository.saveAndFlush(u);
        }
        service.loadUserByUsernameEmail(user.get().getEmail());
    }

    public UserDto getUserByAuthentication(Authentication auth) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        return makeUserDto(repository.findByPhoneNumber(user.getUsername()).orElseThrow(() -> new NoSuchElementException("Auth is null, user not found")));
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
                .build();
    }

    public String getUsernameFromSecurityContextHolder() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) {
                return null;
            }

            org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            return u.getUsername();
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
        if(user.isEmpty()) return null;
        return makeUserDto(user.get());
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
}