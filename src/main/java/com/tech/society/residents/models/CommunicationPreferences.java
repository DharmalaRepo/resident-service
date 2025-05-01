package com.tech.society.residents.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "resident_preferences")
public class CommunicationPreferences extends AuditableModel {

    @Id
    private String id;

    private String societyIdentifier;
    private String residentId;

    private boolean allowSms;
    private boolean allowEmail;
    private boolean allowPush;

    private List<String> quietHours;  // optional: ["22:00-07:00"]

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSocietyIdentifier() {
        return societyIdentifier;
    }

    public void setSocietyIdentifier(String societyIdentifier) {
        this.societyIdentifier = societyIdentifier;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public boolean isAllowSms() {
        return allowSms;
    }

    public void setAllowSms(boolean allowSms) {
        this.allowSms = allowSms;
    }

    public boolean isAllowEmail() {
        return allowEmail;
    }

    public void setAllowEmail(boolean allowEmail) {
        this.allowEmail = allowEmail;
    }

    public boolean isAllowPush() {
        return allowPush;
    }

    public void setAllowPush(boolean allowPush) {
        this.allowPush = allowPush;
    }

    public List<String> getQuietHours() {
        return quietHours;
    }

    public void setQuietHours(List<String> quietHours) {
        this.quietHours = quietHours;
    }
}