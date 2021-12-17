package com.company.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEntity {
    protected String id;
    protected LocalDateTime createdDate;

    public BaseEntity() {
        id = UUID.randomUUID().toString();
        createdDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
