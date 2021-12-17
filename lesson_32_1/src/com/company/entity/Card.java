package com.company.entity;

import com.company.enums.CardStatus;
import com.company.enums.CardType;

import java.time.LocalDateTime;

public class Card extends BaseEntity {
    private String number;
    private String expDate;
    private String password;
    private CardStatus status;
    private CardType type;
    private Long balance; // in tiyin
    private String phone;

// status, type(Humo,UzCard,Visa)


    public Card(String number, String expDate,
                String password, CardStatus status, CardType type,
                Long balance, String phone) {
        this.number = number;
        this.expDate = expDate;
        this.password = password;
        this.status = status;
        this.type = type;
        this.balance = balance;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "number='" + number + '\'' +
                ", expDate='" + expDate + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", status=" +  status+
                '}';
    }
}
