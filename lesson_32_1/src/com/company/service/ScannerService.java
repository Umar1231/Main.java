package com.company.service;

import java.util.Scanner;

public class ScannerService {
    public static Scanner scanner = new Scanner(System.in);

    public static String getString() {
        return scanner.next();
    }

    public static String getString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public static int getInt() {
        try {
            String text = scanner.next();
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Enter number.");
        }
        return -1;
    }

    public static int getInt(String message) {
        System.out.print(message);
        try {
            String text = scanner.next();
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Enter number.");
        }
        return -1;
    }

    public static Long getLong(String message){
        try {
            System.out.println(message);
            String text=scanner.next();
            return Long.parseLong(text);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Enter Number .");
        }
        return -1L;
    }
}
