package com.tech.society.residents.dto;

import java.util.List;

public class BroadcastMessageDTO {
    private String broadcastId;
    private String title;
    private String message;
    private String audience; // ALL, BLOCK, FLAT_SPECIFIC
    private List<String> flatNumbers;
    private List<String> deliveryMethods;

    public BroadcastMessageDTO() {
    }

    public String getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(String broadcastId) {
        this.broadcastId = broadcastId;
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
}
