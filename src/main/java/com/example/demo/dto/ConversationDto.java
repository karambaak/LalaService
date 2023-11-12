package com.example.demo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ConversationDto {
    private String conversationId;
    private String username; //specialist's username
    private Long userId; //specialist's user Id
    private List<ResponseDto> messages;
}
