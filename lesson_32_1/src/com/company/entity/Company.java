package com.company.entity;

import com.company.enums.UserRole;
import com.company.enums.UserStatus;

public class Company extends BaseEntity{
    private String name;
    private String phone;
    private String password;
    private UserRole role;
    private UserStatus status;
    private Card companyCard;

    public Card getCompanyCard() {
        return companyCard;
    }

    public void setCompanyCard(Card companyCard) {
        this.companyCard = companyCard;
    }

    public Company(String name, String phone,
                   String password, UserRole role, UserStatus status, Card companyCard) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.status = status;
        this.companyCard = companyCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
