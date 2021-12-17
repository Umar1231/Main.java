package com.company.service;

import com.company.component.ComponentContainer;
import com.company.entity.User;
import com.company.enums.UserRole;
import com.company.utils.PasswordUtil;

public class UserService {
    private User authorizedUser;

    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    //USER SECTION
    public void userMenu() {
        while (true) {
            showUserMenu();
            int num = ScannerService.getInt();
            switch (num) {
                case 1:ComponentContainer.cardService.start();
                    break;
                case 2:
                    ComponentContainer.transactionService.startP2P();
                    break;
                case 3:
                    ComponentContainer.transactionService.startPayment();
                    break;
                case 4:
                    break;
                case 5:
                    changePassword();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong Option");
                    break;
            }
        }
    }

    private void showUserMenu() {
        System.out.println("*** User Menu *** ");
        System.out.println("1. Card");
        System.out.println("2. P2P"); // kartadan kartaga
        System.out.println("3. Payment"); // oplata
        System.out.println("4. History"); //
        System.out.println("5. Setting");
        System.out.println("0. Exit");
    }


    private void changePassword(){
        String yes=ScannerService.getString("Are you went change Password ? y/n : ");
        if (yes.equals("y"))
        {
            String newPassword;
            do {
                newPassword = ScannerService.getString("Enter new Password: ");
            }while (!PasswordUtil.isValidPassword(newPassword));
            authorizedUser.setPassword(newPassword);
            System.out.println("Succesfull");
        }
    }


    //ADMIN SECTION
//
//    public void showAdminMenu() {
//        System.out.println("*** Admin Menu *** ");
//        System.out.println("1. Manage Company");
//        System.out.println("2. Manage User");
//        System.out.println("0. Exit");
//    }
//
//    public void adminMenu() {
//        while (true) {
//            showAdminMenu();
//            int num = ScannerService.getInt();
//            switch (num) {
//                case 1:
//                    addCompany();
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    break;
//                case 0:
//                    return;
//                default:
//                    System.out.println("Wrong Option");
//                    break;
//            }
//        }
//    }
    private void addCompany(){
        ComponentContainer.companyService.start();
    }





}
