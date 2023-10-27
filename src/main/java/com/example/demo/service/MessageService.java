package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.entities.Message;
import com.example.demo.entities.Notification;
import com.example.demo.entities.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final NotificationRepository notificationRepository;
    private final PostService postService;

    public List<MessageDto> getAll() {
        User user = userService.getUserFromSecurityContextHolder();
        if (user != null) {
            List<Message> all = messageRepository.findAllBySender(user);
            List<Message> all2 = messageRepository.findAllByReceiver(user);
//            List<ResponseDto> all3 = postService.getSpecialistResponses(//specialistId);
//            List< ConversationDto > all4 = postService.getCustomerConversations(//postId)
            all.addAll(all2);
            return all.stream().map(this::makeDtoFromMessage).toList();
        }
        return null;
    }

    private MessageDto makeDtoFromMessage(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .receiverId(message.getReceiver().getId())
                .senderName(message.getSender().getUserName())
                .senderPhoto(message.getSender().getPhoto())
                .messageText(message.getMessageText())
                .dateTime(message.getDateTime())
                .build();
    }

    public List<NotificationDto> getAllNotifications() {
        User user = userService.getUserFromSecurityContextHolder();
        if (user != null) {
            List<Notification> list = notificationRepository.findAllByUser(user);
            return list.stream().map(this::makeDtoFromNotification).toList();
        }
        return null;
    }

    private NotificationDto makeDtoFromNotification(Notification n) {
        return NotificationDto.builder()
                .id(n.getId())
                .userId(n.getUser().getId())
                .notificationText(n.getNotificationText())
                .notificationDate(n.getNotificationDate())
                .build();
    }
}

