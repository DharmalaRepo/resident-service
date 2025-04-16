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
@Document(collection = "alerts_reminders")
public class AlertReminder {
    @Id
    private String id;
    private int customId;
    private String title;
    private String message;
    private LocalDateTime triggerDate;
    private List<String> recipients;
    private String createdBy;
    private String deliveryMethod; // EMAIL, WHATSAPP, SMS
    private boolean isSent;
    private int isActive;
}