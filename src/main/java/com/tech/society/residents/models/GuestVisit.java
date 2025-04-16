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
@Document(collection = "guest_visits")
public class GuestVisit {
    @Id
    private String id;
    private int customId;
    private String guestName;
    private String contactNumber;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private int visitingResidentId;
    private String flatNumber;
    private String purpose;
    private String status; // IN or OUT
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;
    private int isActive;
}