package com.example.demo.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, max = 255, message = "Message must be between 1 and 255 characters")
    private String messageText;
    @Past(message = "Time must be in the past or current")
    private LocalDateTime dateTime;

}
