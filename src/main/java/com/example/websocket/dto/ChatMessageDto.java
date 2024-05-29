package com.example.websocket.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {


    private String model;
    private String created_at;
    private String response;
    private String done;
}
