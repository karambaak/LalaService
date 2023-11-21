package com.example.demo.steps;

import com.example.demo.dto.MessageBundleDto;
import com.example.demo.entities.User;
import com.example.demo.service.MessageService;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@CucumberContextConfiguration
@CucumberOptions(plugin = {"pretty"})
public class MessageStepDef {

    @Autowired
    private MessageService messageService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Когда("я запрашиваю сообщения пользователя х")
    public void яЗапрашиваюСообщенияПользователяХ() {
        User user = mockExistingUser();
    }

    private User mockExistingUser() {
        User user = new User();
        user.setId(1L);
        user.setPhoneNumber("123");
        user.setPassword("qwe");
        user.setEnabled(true);
        user.setUserType("SPECIALIST");
        return user;
    }


    private User mockNonExistingUser() {
        return null;
    }

    @Тогда("я получаю список сообщений")
    public void яПолучаюСписокСообщений() {
        User user = mockExistingUser();
        System.out.println(user.getId());
        List<MessageBundleDto> messages = messageService.getAllMessagesByUser(user);
        System.out.println(messages.size());
        assertFalse(messages.isEmpty());
    }

    @Тогда("я пустой список сообщений")
    public void яПустойСписокСообщений() {
        User user = mockNonExistingUser();
        List<MessageBundleDto> messages = messageService.getAllMessagesByUser(user);
        assertTrue(messages.isEmpty());
    }
}
