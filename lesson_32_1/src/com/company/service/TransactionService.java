package com.company.service;

import com.company.component.ComponentContainer;
import com.company.entity.Card;
import com.company.entity.Company;
import com.company.entity.Transaction;
import com.company.enums.CardStatus;
import com.company.enums.TransactionType;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TransactionService {
    private List<Transaction> transactions=new LinkedList<>();
    private Company MyCompany;

    public void startP2P() {
        String senderCardNumber = ScannerService.getString("Enter Card Number: ");
        String receiverCardNumber = ScannerService.getString("Enter Sender Card: ");
        Long amount = ScannerService.getLong("Enter Amount: ");

        Card senderCard = ComponentContainer.cardService.getCardByNumber(senderCardNumber);
        Card receiverCard = ComponentContainer.cardService.getCardByNumber(receiverCardNumber);

        if (senderCard == null || receiverCard == null) {
            System.out.println("Card not found !");
            return;
        }

        if (!senderCard.getStatus().equals(CardStatus.ACTIVE) ||
                !receiverCard.getStatus().equals(CardStatus.ACTIVE)) {
            System.out.println("Card NOt Active !");
            return;
        }

        Double comission=ComissionService.getTransactionComission(senderCard.getType(),receiverCard.getType());
        long comissionAmount=(long)((amount/100)*comission);

        if (senderCard.getBalance() < (amount+comissionAmount)) {
            System.out.println("Not Enough Balance !");
            return;
        }

        senderCard.setBalance(senderCard.getBalance() - amount);
        receiverCard.setBalance(receiverCard.getBalance() + amount);


        Transaction newTransaction = new Transaction(senderCardNumber,
                receiverCardNumber, amount, TransactionType.P2P);
        transactions.add(newTransaction);
        chontagimga(comissionAmount);
        System.out.println("P2P Succesfull");
    }

    public void startPayment(){
        showCompanyList();
        int n;
        do {
            n=ScannerService.getInt("Choose Company: ");
        }while (n>AuthService.companyMap.size());

        Company company=getCompanyByNumber(n);
        String phone=ScannerService.getString("Enter phone: ");
        String senderCardNumber = ScannerService.getString("Enter Card Number: ");
        Long amount = ScannerService.getLong("Enter Amount: ");

        Card senderCard = ComponentContainer.cardService.getCardByNumber(senderCardNumber);
        Card receiverCard = company.getCompanyCard();

        if (senderCard == null || receiverCard == null) {
            System.out.println("Card or Company not found !");
            return;
        }

        if (!senderCard.getStatus().equals(CardStatus.ACTIVE)) {
            System.out.println("Card or Copany Not Active !");
            return;
        }

        Double comission=1d;
        long comissionAmount=(long)((amount/100)*comission);

        if (senderCard.getBalance() < amount) {
            System.out.println("Not Enough Balance !");
            return;
        }

        senderCard.setBalance(senderCard.getBalance()-amount);
        receiverCard.setBalance(receiverCard.getBalance()+amount-comissionAmount);
        Transaction newTransaction = new Transaction(senderCardNumber,
                company.getName(), amount, TransactionType.PAYMENT);
        transactions.add(newTransaction);
        chontagimga(comissionAmount);
        ComponentContainer.smsService.sendSms(phone,amount);
        System.out.println("Successfull");
    }

    private void chontagimga(long pul){
        Card myCard= MyCompany.getCompanyCard();
        myCard.setBalance(myCard.getBalance()+pul);
        System.out.println("URAA CHONTAGIMGA "+myCard.getBalance());
    }

    private void showCompanyList(){
        int i=1;
        for(Company c:AuthService.companyMap.values())
        {
            System.out.println(i++ +". "+c.getName());
        }
    }

    private Company getCompanyByNumber(int n){
        int i=1;
        for(Company c:AuthService.companyMap.values())
        {
            if(i==n)
                return c;
            i++;
        }
        return null;
    }

    public void setMyCompany(Company myCompany) {
        MyCompany = myCompany;
    }
}
