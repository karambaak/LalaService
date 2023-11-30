package com.example.demo.service;

import com.example.demo.repository.UpdateCountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UpdateCountsService {
    private final UpdateCountsRepository repository;

    public void deleteCount(long id){
        repository.deleteById((int) id);
    }
    public boolean hasThirtyDaysPassed(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -30);

        Timestamp thirtyDaysAgoTimestamp = new Timestamp(calendar.getTime().getTime());
        return timestamp.before(thirtyDaysAgoTimestamp);
    }
}
