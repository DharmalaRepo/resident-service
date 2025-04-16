package com.tech.society.residents.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "broadcast_messages")
public class BroadcastMessage {
    @Id
    private String id;
    private int customId;
    private String title;
    private String message;
    private String audience; // ALL or SELECTED
    private List<String> flatNumbers;
    private String sentBy;
    private LocalDateTime sentAt;
    private String deliveryMethod; // EMAIL / WHATSAPP / SMS
    private int isActive;
}