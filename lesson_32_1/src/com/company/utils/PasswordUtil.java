package com.company.utils;

public class PasswordUtil {

    public static boolean isValidPassword(String password) {
        if (password.length() <= 4) {
            return false;
        }

        boolean digit = false;
        boolean lowerCase = false;
        boolean upperCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c))
                digit = true;

            if (Character.isLowerCase(c))
                lowerCase = true;

            if (Character.isUpperCase(c))
                upperCase = true;
        }

        return digit && lowerCase && upperCase;

    }
}
