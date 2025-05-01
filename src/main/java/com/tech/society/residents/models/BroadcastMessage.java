package com.tech.society.residents.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "broadcast_messages")
public class BroadcastMessage extends AuditableModel {

    @Id
    private String id;

    private String broadcastId;
    private String societyIdentifier;

    private String title;
    private String message;
    private String audience;                   // ALL, BLOCK, FLAT_SPECIFIC
    private List<String> flatNumbers;          // if targeted
    private List<String> deliveryMethods;

    private LocalDateTime sentAt;
    private String sentBy;

    private boolean active = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(String broadcastId) {
        this.broadcastId = broadcastId;
    }

    public String getSocietyIdentifier() {
        return societyIdentifier;
    }

    public void setSocietyIdentifier(String societyIdentifier) {
        this.societyIdentifier = societyIdentifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public List<String> getFlatNumbers() {
        return flatNumbers;
    }

    public void setFlatNumbers(List<String> flatNumbers) {
        this.flatNumbers = flatNumbers;
    }

    public List<String> getDeliveryMethods() {
        return deliveryMethods;
    }

    public void setDeliveryMethods(List<String> deliveryMethods) {
        this.deliveryMethods = deliveryMethods;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}