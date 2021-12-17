package com.company.entity;

import com.company.enums.TransactionType;

public class Transaction extends BaseEntity{
    private String senderCardNumber;
    private  String receiverCardNumber;

    private Long amount;
    private Double commision; // comission persentage foiz komissiya
    private TransactionType type;

    public Transaction(String senderCardNumber, String receiverCardNumber, Long amount,TransactionType type) {
        this.senderCardNumber = senderCardNumber;
        this.receiverCardNumber = receiverCardNumber;
        this.amount = amount;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getReceiverCardNumber() {
        return receiverCardNumber;
    }

    public void setReceiverCardNumber(String receiverCardNumber) {
        this.receiverCardNumber = receiverCardNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getCommision() {
        return commision;
    }

    public void setCommision(Double commision) {
        this.commision = commision;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
