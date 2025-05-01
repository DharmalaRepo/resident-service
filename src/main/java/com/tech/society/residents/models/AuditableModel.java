package com.tech.society.residents.models;

import java.time.LocalDateTime;

public class AuditableModel {
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public void auditCreate(String username) {
        this.createdBy = username;
        this.createdDate = LocalDateTime.now();
        this.modifiedBy = username;
        this.modifiedDate = LocalDateTime.now();
    }

    public void auditUpdate(String username) {
        this.modifiedBy = username;
        this.modifiedDate = LocalDateTime.now();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
