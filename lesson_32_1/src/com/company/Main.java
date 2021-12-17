package com.company;

import com.company.service.ScannerService;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        BankSystem bankSystem = new BankSystem();
        bankSystem.init();
        bankSystem.start();

    }
}
