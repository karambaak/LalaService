package com.example.demo.service;

import com.example.demo.dto.ContactsDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class ContactsServiceTest {
    @MockBean
    private ContactsService contactsService;

    @Test
    public void test_get_all_method() {
        List<ContactsDto> list = contactsService.getAll();
        assertNotNull(list);
    }
}
