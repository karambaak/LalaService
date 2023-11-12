package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ContactType {
    PHONE ("Телефон"),
    TELEGRAM ("Telegram"),
    WHATSAPP ("WhatsApp"),
    INSTAGRAM ("Instagram"),
    FACEBOOK ("Facebook"),
    VK ("ВКонтакте"),
    OK ("Одноклассники"),
    LINKEDIN ("LinkedIn"),
    OTHER ("Другое");

    private String value;

    ContactType(String value) {

    }
}
