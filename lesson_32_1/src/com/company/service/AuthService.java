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
import com.company.utils.PasswordUtil;
import com.company.utils.PhoneUtil;
import com.sun.deploy.cache.BaseLocalApplicationProperties;

import java.time.LocalDateTime;
import java.util.*;

public class AuthService {
    public static Map<String,User> userMap = new HashMap<>();
    public static Map<String,Company> companyMap = new HashMap<>();


    public void registration() {
        System.out.println("*** Registration ***");
        String name = ScannerService.getString("Enter Name: ");
        String surname = ScannerService.getString("Enter Surname: ");
        String phone;
        do {
            phone = ScannerService.getString("Enter Phone: ");
            if (!PhoneUtil.isValidPhone(phone)) {
                System.out.println("Phone not valid.");
                continue;
            }
            if (isPhoneExists(phone)) {
                System.out.println("Phone is exist.");
                continue;
            }
            break;
        } while (true);

        String password;
        do {
            password = ScannerService.getString("Enter Password: ");
        } while (!PasswordUtil.isValidPassword(password));

        String confirm;
        do {
            confirm = ScannerService.getString("Confirm Password: ");
        } while (!password.equals(confirm));

        Sms sms;
        String code;
        boolean shouldContinue;
        do {
            shouldContinue = false;
            sms = SmsService.sendSms(phone);
            code = ScannerService.getString("Enter Sms Code: ");

            if (!sms.getContent().equals(code)) {
                shouldContinue = true;
                continue;
            }

            if (!sms.getCreatedDate().plusMinutes(1).isAfter(LocalDateTime.now())) {
                shouldContinue = true;
            }

        } while (shouldContinue);

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole(UserRole.CLIENT);
        user.setStatus(UserStatus.ACTIVE);

        userMap.put(phone,user);
        System.out.println("Registration completed");
    }

//    public static Company registrationCompany() {
//        System.out.println("*** Registration Company ***");
//
//        String name = ScannerService.getString("Enter Name: ");
//
//        String phone;
//
//        do {
//            phone = ScannerService.getString("Enter Phone: ");
//            if (!PhoneUtil.isValidPhone(phone)) {
//                System.out.println("Phone not valid.");
//                continue;
//            }
//            break;
//        } while (true);
//
//        String password;
//        do {
//            password = ScannerService.getString("Enter Password: ");
//        } while (!PasswordUtil.isValidPassword(password));
//
//        String confirm;
//        do {
//            confirm = ScannerService.getString("Confirm Password: ");
//        } while (!password.equals(confirm));
//
//
//        Company company = new Company();
//        company.setName(name);
//        company.setPhone(phone);
//        company.setPassword(password);
//        company.setRole(UserRole.COMPANY);
//        company.setStatus(UserStatus.NOT_ACTIVE);
//
//        companyMap.put(phone,company);
//        System.out.println("Registration completed");
//        return company;
//    }


    public static boolean isPhoneExists(String phone) {
        for(User u:userMap.values())
        {
            if(u.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    public User logIn() {
        System.out.println("*** LogIn ***");
        System.out.print("Enter Phone: ");
        String phone = ScannerService.getString();

        System.out.print("Enter Password: ");
        String password = ScannerService.getString();
        for (User user : userMap.values()) {
            if (user.getPhone().equals(phone) &&
                    user.getPassword().equals(password) &&
                    user.getStatus().equals(UserStatus.ACTIVE)) {
                System.out.println("Successfully");
                System.out.println("# Welcome: " + user.getName());
                return user;
            }
        }
        System.out.println("Phone or Password is Wrong.");
        return null;
    }


    public void init() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Alish");
        user.setSurname("Alishov");
        user.setPhone("998123456789");
        user.setPassword("123123Aa");
        user.setRole(UserRole.ADMIN);
        user.setStatus(UserStatus.ACTIVE);

        userMap.put(user.getPhone(),user);

        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Ali");
        user.setSurname("Aliyev");
        user.setPhone("998915721213");
        user.setPassword("1234Aa");
        user.setRole(UserRole.CLIENT);
        user.setStatus(UserStatus.ACTIVE);
        userMap.put(user.getPhone(),user);
        createCompanies();
    }


    public void createCompanies(){
        Card comCard1=new Card("9860111111111111","2021","4444", CardStatus.ACTIVE, CardType.UZCARD,0L,"998944444444");
        Card comCard2=new Card("4600111111111112","2021", "7777",CardStatus.ACTIVE, CardType.UZCARD,201L,"998977777777");
        Card comCard3=new Card("1111111111111113","2021", "1111",CardStatus.ACTIVE, CardType.UZCARD ,201L,"998911111111");
        Card comCard4=new Card("1111111111111119","2021", "9999",CardStatus.ACTIVE, CardType.UZCARD ,201L,"998999999999");

        ComponentContainer.cardService.cardList.add(comCard1);
        ComponentContainer.cardService.cardList.add(comCard2);
        ComponentContainer.cardService.cardList.add(comCard3);
        ComponentContainer.cardService.cardList.add(comCard4);

        Company ucell=new Company("Ucell","998944444444",
                "1234Aa",UserRole.COMPANY,UserStatus.ACTIVE,comCard1);
        Company mobiuz=new Company("Mobiuz","998977777777",
                "1234Aa",UserRole.COMPANY,UserStatus.ACTIVE,comCard2);
        Company beeline=new Company("Beeline","998911111111",
                "1234Aa",UserRole.COMPANY,UserStatus.ACTIVE,comCard3);
        Company uzmobile=new Company("Uzmobile","998999999999",
                "1234Aa",UserRole.COMPANY,UserStatus.ACTIVE,comCard4);

        companyMap.put(ucell.getPhone(),ucell);
        companyMap.put(mobiuz.getPhone(),mobiuz);
        companyMap.put(beeline.getPhone(),beeline);
        companyMap.put(uzmobile.getPhone(),uzmobile);


        Card chontak=new Card("1111111111111111","2021", "8845",CardStatus.ACTIVE, CardType.UZCARD, 1000L,"998945488845");

        Company chontakCompany=new Company("Sohib Corporation",
                "998945488845","8845",
                UserRole.COMPANY,UserStatus.ACTIVE,chontak);

        ComponentContainer.transactionService.setMyCompany(chontakCompany);
    }





}
