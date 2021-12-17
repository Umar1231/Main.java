package com.company.utils;

public class PhoneUtil {
    public static boolean isValidPhone(String phone) {
        if (phone.length() != 12 || !phone.startsWith("998")) {
            return false;
        }
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
