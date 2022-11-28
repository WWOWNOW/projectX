package com.javamentor.qa.platform.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SingleChatDto {

    private Long id;
    private Long userRecipientId;
    private Long userSenderId;
    private String name;
    private String image;
    private String lastMessage;
    private LocalDateTime persistDateTimeLastMessage;

    public SingleChatDto(Long id,Long userRecipientId,Long userSenderId, String name ,String image, String lastMessage, LocalDateTime persistDateTimeLastMessage) {
        this.id = id;
        this.userRecipientId = userRecipientId;
        this.userSenderId = userSenderId;
        this.name = name;
        this.image = image;
        this.lastMessage = lastMessage;
        this.persistDateTimeLastMessage = persistDateTimeLastMessage;
    }
}
