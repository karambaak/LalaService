package com.example.demo.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UpdateCountsServiceTest {
    @Autowired
    private UpdateCountsService updateCountsService;

    @Before
    private void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_false() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -20);

        Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
        assertFalse(updateCountsService.hasThirtyDaysPassed(timestamp));
    }

    @Test
    public void should_get_true() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -60);

        Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
        assertTrue(updateCountsService.hasThirtyDaysPassed(timestamp));
    }

}
