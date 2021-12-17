package com.company;

import com.company.component.ComponentContainer;
import com.company.entity.Company;
import com.company.entity.User;
import com.company.enums.UserRole;
import com.company.service.CompanyService;
import com.company.service.ScannerService;

public class BankSystem {

    public void start() {
        while (true) {
            showMainMenu();
            int num = ScannerService.getInt();
            switch (num) {
                case 1:
                    User user = ComponentContainer.authService.logIn();
                    if (user != null) {
                        if (user.getRole().equals(UserRole.CLIENT)) {
                            ComponentContainer.userService.setAuthorizedUser(user);
                            ComponentContainer.userService.userMenu();
                        }
//                        else if (user.getRole().equals(UserRole.ADMIN)) {
//                            ComponentContainer.userService.adminMenu();
//                        }
                    }
                    break;
                case 2:
                    ComponentContainer.authService.registration();
                    break;
                case 3:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong Option");
                    break;
            }
        }
    }

    public void showMainMenu() {
        System.out.println("***** Menu ***** ");
        System.out.println("1. LogIn");
        System.out.println("2. Registration");
        System.out.println("3. Currency");
        System.out.println("0. Exit");
        System.out.print("Choose Option: ");
    }

    public void init() {
        ComponentContainer.authService.init();
    }

}
