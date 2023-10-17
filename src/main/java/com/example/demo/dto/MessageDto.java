package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MessageDto {

    private Long id;
    private Long receiverId;
    private String senderName;
    private String senderPhoto;
    private String messageText;
    private String lastMessage;
    private LocalDateTime dateTime;
    private LocalDateTime lastMessageDateTime;
}
