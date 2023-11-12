package com.example.demo.repository;

import com.example.demo.entities.Message;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySender(User user);

    List<Message> findAllByReceiver(User user);
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);



}
