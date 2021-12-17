package com.company.service;

import com.company.component.ComponentContainer;
import com.company.entity.Card;
import com.company.entity.Company;
import com.company.entity.Sms;
import com.company.entity.User;
import com.company.enums.CardStatus;
import com.company.enums.CardType;
import com.company.enums.UserRole;
import com.company.enums.UserStatus;
import com.company.utils.PhoneUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CardService{
    List<Card> cardList=new LinkedList<>();

    public void start(){
        showCardMenu();
        int action=ScannerService.getInt();
        switch (action){
            case 1:addCard();break;
            case 2:showMyCards();break;
            case 3:changeCardStatus();break;
            case 4:deleteCard();break;
            case 0:return;
        }
    }


    private void showCardMenu(){
        System.out.println("**** CARD MENU ****");
        System.out.println("1. Add Card");
        System.out.println("2. Card List");
        System.out.println("3. Change Card Status");
        System.out.println("4. Delete Card");
        System.out.println("0. Exit");
    }


    public void addCard() {
        System.out.println("*** Adding a Card ***");

        String number ="1231231231231231";
        do{
            System.out.print("Enter Card number: ");
            number=ScannerService.scanner.next();
        }while(isValidCardNumber(number, 16));

        String expDate="2021";
        do{
            System.out.print("Enter Card Expired Date: ");
            expDate=ScannerService.scanner.next();
        }while(isValidCardNumber(expDate, 4));

        System.out.print("Enter Card password: ");
        String password = ScannerService.scanner.next();

        String phone;
        do{
            System.out.print("Eneter Autorized Phone:");
            phone=ScannerService.scanner.next();
        }while(!PhoneUtil.isValidPhone(phone) || AuthService.userMap.get(phone)==null);

        Card card = new Card(number,expDate,password, CardStatus.ACTIVE,getCardType(number),100000L,phone);

        for (User user:AuthService.userMap.values())
        {
            if(user.getPhone().equals(phone))
            {
                System.out.println("Adding Card Succesfull !");
                card.setStatus(CardStatus.ACTIVE);
                cardList.add(card);
                return;
            }
        }

//
//        Sms sms=SmsService.sendSms(phone);
//        String currSms;
//        do{
//            currSms=ScannerService.getString("Enter Sms:");
//        }while (!sms.getContent().equals(currSms));
//        if(AuthService.userMap.get(phone)!=null)
//        {
//            System.out.println("");
//        }
//        card.setUserId(AuthService.userMap.get(phone).getId());
//        System.out.println("Adding Card Succesfull !");
        cardList.add(card);
    }

    private void showMyCards(){
        User user= ComponentContainer.userService.getAuthorizedUser();
        int i=0;
        for (Card c:cardList)
        {
            if(c.getPhone().equals(user.getPhone()))
                System.out.println(++i +" "+c);
        }
    }

    private Card getMyCardByNumber(int n,String phone){
        int i=0;
        for (Card c:cardList)
        {
            i++;
            if(c.getPhone().equals(phone) && i==n)
                return c;
        }
        return null;
    }

    public boolean isValidCardNumber(String number,int n) {
        if(number.length()!=n)
            return true;
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public void addCompanyCard(){
        System.out.println("*** Adding a Card ***");

        String number ;
        do{
            System.out.print("Enter Card number: ");
            number=ScannerService.scanner.next();
        }while(isValidCardNumber(number, 16));

        String expDate;
        do{
            System.out.print("Enter Card Expired Date: ");
            expDate=ScannerService.scanner.next();
        }while(isValidCardNumber(expDate, 4));

        System.out.print("Enter Card password: ");
        String password = ScannerService.scanner.next();

        String phone;
        do{
            System.out.print("Eneter Phone:");
            phone=ScannerService.scanner.next();
        }while(!PhoneUtil.isValidPhone(phone));

        Card card = new Card(number,expDate,password,CardStatus.ACTIVE,getCardType(number),1000L,phone);

        for (Company company:AuthService.companyMap.values())
        {
            if(company.getPhone().equals(phone))
            {
                System.out.println("Adding Card Succesfull !");
                company.setStatus(UserStatus.ACTIVE);
                cardList.add(card);
                return;
            }
        }
        Sms sms=SmsService.sendSms(phone);
        String currSms;
        do{
            currSms=ScannerService.getString("Enter Sms:");
        }while (!sms.getContent().equals(currSms));
        System.out.println("Adding Card Succesfull !");
        AuthService.companyMap.get(phone).setStatus(UserStatus.ACTIVE);
        cardList.add(card);
    }


    private CardType getCardType(String number){
        if(number.startsWith("9860"))
            return CardType.HUMO;
        else if (number.startsWith("4600"))
            return CardType.VISA;
        else return CardType.UZCARD;
    }

    public void showCompanyBalance(String phone){
        int i=0;
        for (Card c:cardList)
        {
            if(c.getPhone().equals(phone))
                System.out.println(i++ +" "+c);
        }
    }

    private void changeCardStatus(){
        String userId=ComponentContainer.userService.getAuthorizedUser().getId();
        showMyCards();
        int n=ScannerService.getInt("Choose your card : ");
        Card card=getMyCardByNumber(n,userId);

        if(card.getStatus().equals(CardStatus.ACTIVE)) card.setStatus(CardStatus.BLOCK);
        else if(card.getStatus().equals(CardStatus.BLOCK)) card.setStatus(CardStatus.ACTIVE);
        System.out.println("Succesfull !");
    }

    private void deleteCard(){
        String userId=ComponentContainer.userService.getAuthorizedUser().getId();
        showMyCards();
        int n;
        do {
            n=ScannerService.getInt("Choose your card : ");
        }while (n<cardList.size());
        Card card=getMyCardByNumber(n,userId);
        cardList.remove(n-1);
        System.out.println("Succesfull !");
    }


    public Card getCardByNumber(String number){
        for (Card c:cardList)
        {
            if(c.getNumber().equals(number))
                return c;
        }
        return null;
    }

}
