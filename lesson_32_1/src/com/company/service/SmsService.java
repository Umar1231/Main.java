package com.company.service;

import com.company.entity.Sms;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SmsService {
    private static List<Sms> smsList = new LinkedList<>();

    public static Sms sendSms(String phone) {
        String code = getRandomNumber();
        Sms sms = new Sms();
        sms.setContent(code);
        sms.setPhone(phone);
        smsList.add(sms);
        System.out.println("Sending sms... " + sms);
        return sms;
    }

    public void sendSms(String phone,long balance) {
        Sms sms = new Sms();
        sms.setPhone(phone);
        System.out.println("Hisobingiz  "+ LocalDateTime.now()+" da"
        + balance + " UZS ga To'ldirildi");
    }

    private static String getRandomNumber() {
        Random r = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            s.append(r.nextInt(9)); // 0...9
        }
        return s.toString();
    }


    public void showSmsList() {
        // ....
    }

    public List<Sms> getUserSmsList(String phone){
        return null;
    }
}
