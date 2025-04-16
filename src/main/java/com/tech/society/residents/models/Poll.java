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
@Document(collection = "polls")
public class Poll {
    @Id
    private String id;
    private int customId;
    private String question;
    private List<String> options;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String createdBy;
    private List<String> votes; // resident IDs or usernames
    private String status; // OPEN, CLOSED
    private boolean isAnonymous;
}