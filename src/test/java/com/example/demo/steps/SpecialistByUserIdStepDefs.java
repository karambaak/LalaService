package com.example.demo.steps;

import com.example.demo.service.SpecialistService;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.junit.CucumberOptions;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@CucumberOptions(plugin = {"pretty"})
public class SpecialistByUserIdStepDefs {
    @Autowired
    private SpecialistService specialistService;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Когда("я запрашиваю имя специалиста по идентификатору пользователя")
    public void i_search_specialist_by_user_id() {
        specialistService.getSpecialistIdByUserId(1L);
    }

    @Тогда("я получаю идентификатор специалиста")
    public void i_get_a_specialist_id() {
        Long specialistId = specialistService.getSpecialistIdByUserId(1L);
        assertNotNull(specialistId);
    }


    @Тогда("я получаю ошибку")
    public void i_get_an_error() {
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> specialistService.getSpecialistIdByUserId(1000L)
        );
        assertEquals("Specialist not found", exception.getMessage());

    }
}
