package com.company.service;

import com.company.component.ComponentContainer;
import com.company.entity.Company;
import com.company.enums.UserStatus;

public class CompanyService {

    public void start(){
        showmenu();
        int action=ScannerService.getInt();
        switch (action){
            case 1:
                //AuthService.registrationCompany();
                ComponentContainer.cardService.addCompanyCard();
                break;
            case 2:
                showCompanyList();
                break;
            case 3:
                logInCompany();
                break;
        }
    }

    private void showCompanyList() {
        int i=0;
        for (Company c:AuthService.companyMap.values())
        {
            if(c.getStatus().equals(UserStatus.ACTIVE))
                System.out.println(++i + " "+c.getName()+ " "+c.getPhone());
        }

    }

    private String foo(int n) {
        int i=0;
        for (Company c:AuthService.companyMap.values())
        {
            if(i==n)
                return c.getPhone();
            i++;
        }
        return null;
    }

    private void showmenu() {
        System.out.println("*** Company Manage Menu ***");
        System.out.println("1. Add Company");
        System.out.println("2. Company List");
        System.out.println("3. Log-IN Company");
        System.out.println("4. Exit");
    }

    private void showmenu1(String name) {
        System.out.println("You In "+ name+ "Profile");
        System.out.println("1. Rename Company");
        System.out.println("2. Status Company");
        System.out.println("3. Balance");
        System.out.println("4. Exit");
    }

    private void logInCompany() {
        System.out.print("Choose Company :");
        int n=ScannerService.getInt();
        String phone=foo(n);
        if (phone!=null) {
            Company company = AuthService.companyMap.get(phone);
            changeCompany(company);
        }
    }

    private void changeCompany(Company company){
        showmenu1(company.getName());
        int action=ScannerService.getInt();
        switch (action){
            case 1:renameCompany(company);
            break;
            case 2:changeStatusCompany(company);
            case 3:showBalance(company);
        }
    }

    private void renameCompany(Company company){
        String s=ScannerService.getString("Enter Name: ");
        company.setName(s);
        System.out.println("Succesfull !");
    }

    private void changeStatusCompany(Company company){
        System.out.println(company.getName()+" Status: "+company.getStatus());
        if(company.getStatus().equals(UserStatus.ACTIVE))
            company.setStatus(UserStatus.NOT_ACTIVE);
        else if(company.getStatus().equals(UserStatus.NOT_ACTIVE))
            company.setStatus(UserStatus.ACTIVE);
        System.out.println("Succesfull !");
    }

    private void showBalance(Company company) {
        if(company.getStatus().equals(UserStatus.NOT_ACTIVE))
        {
            System.out.println("This Company NOt Active ( has not card) ");
            return;
        }
        CardService cardService=new CardService();
        cardService.showCompanyBalance(company.getPhone());
    }

}
