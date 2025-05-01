package com.tech.society.residents.dto;

import java.util.List;

public class CommunicationPreferencesDTO {
    private boolean allowSms;
    private boolean allowEmail;
    private boolean allowPush;
    private List<String> quietHours;

    public CommunicationPreferencesDTO() {
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