package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MessageBundleDto {
    private String id;
    private String senderPhoto;
    private String senderName;
    private String lastMessageText;
    private String lastMessageDateTime;
}
