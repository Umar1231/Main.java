package com.company.component;

import com.company.service.*;

public interface ComponentContainer {
    public static final UserService userService = new UserService();
    public static final AuthService authService = new AuthService();
    public static final SmsService smsService = new SmsService();
    public static final CardService cardService = new CardService();
    public static final CompanyService companyService = new CompanyService();
    TransactionService transactionService = new TransactionService();

}
