package com.example.demo.steps;

import com.example.demo.entities.Specialist;
import com.example.demo.entities.User;
import com.example.demo.service.SpecialistService;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.junit.CucumberOptions;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberOptions(plugin = {"pretty"})
public class SpecialistStepDefs {
    @Autowired
    private SpecialistService specialistService;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Когда("я запрашиваю имя специалиста")
    public void i_request_specialist_name() {
        String s = specialistService.findSpecialistName(mockSpecialist1());
    }

    @Тогда("я получаю название компании")
    public void i_receive_company_name() {
        String s = specialistService.findSpecialistName(mockSpecialist1());
        assertEquals("Test", s);
    }

    @Тогда("я получаю имя пользователя")
    public void i_receive_user_name() {
        String s = specialistService.findSpecialistName(mockSpecialist2());
        assertEquals("UserName", s);
    }

    private User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setPhoneNumber("123");
        user.setPassword("qwe");
        user.setEnabled(true);
        user.setUserName("UserName");
        user.setUserType("SPECIALIST");
        return user;
    }

    private Specialist mockSpecialist1() {
        Specialist s = new Specialist();
        s.setUser(mockUser());
        s.setCompanyName("Test");
        return s;
    }

    private Specialist mockSpecialist2() {
        Specialist s = new Specialist();
        s.setUser(mockUser());
        return s;
    }
}
