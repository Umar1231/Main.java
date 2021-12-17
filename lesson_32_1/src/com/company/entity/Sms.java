package com.company.entity;

import java.time.LocalDateTime;

public class Sms extends BaseEntity {
    private String phone;
    private String content;

    public Sms() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "{" +
                "phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
