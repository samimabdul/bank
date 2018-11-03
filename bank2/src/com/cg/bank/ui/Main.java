package com.cg.bank.ui;

import java.util.Scanner;

import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankException;
import com.cg.bank.exception.AmountException;
import com.cg.bank.exception.PhoneNumberException;
import com.cg.bank.exception.NameException;
import com.cg.bank.service.BankService;
import com.cg.bank.service.BankServiceImpl;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner sc = new Scanner(System.in);
        BankService service = null;

        int ch;
        do {

            System.out.println("1. Create account for new consumer...");
            System.out.println("2. Show balance...");
            System.out.println("3. Fund Transfer...");
            System.out.println("4. Deposit amount into account...");
            System.out.println("5. Withdraw amount from account...");
            System.out.println("6. Exit...");
           ch = sc.nextInt();

            switch (ch) {
            case 1:

                service = new BankServiceImpl();

                System.out.println("Enter Customer Name: ");
                String name = sc.next();
                System.out.println("Enter Mobile Number: ");
                String mobNo = sc.next();
                System.out.println("Enter Initial Amount: ");
                double amt = sc.nextDouble();

                Customer c = new Customer(name, mobNo, amt);
                Customer c1 = null;
                try {
                    if(service.validateAll(c))
                            c1 = service.createAccount(c);
                    System.out.println("Successfully created new account for "
                            + c1.getCustomerName() + " with " + "Mobile Number "
                            + c1.getMobileNumber());
                } catch (BankException | NameException | AmountException | PhoneNumberException e) {
                    // TODO Auto-generated catch block
                    System.err.println(e);
                }
                break;

            case 2:

                service = new BankServiceImpl();
                System.out.println("Enter an existing mobile number: ");
                String mobNoShow = sc.next();

                double bal = 0;
                try {
                    if(service.validatePhoneNumber(mobNoShow))
                        bal = service.showBalance(mobNoShow);
                    System.out.println("Available balance for the mobile number "
                            + mobNoShow + " is " + bal);
                } catch (PhoneNumberException | BankException e) {
                    // TODO Auto-generated catch block
                    System.err.println(e);
                }

                break;

            case 3:

                service = new BankServiceImpl();
                Customer funds = null;

                System.out.println("Enter your mobile number: ");
                String sourceMobileNo = sc.next();
                System.out.println("Enter recipient's mobile number: ");
                String targetMobileNo = sc.next();
                System.out.println("Enter the amount that to be transfered: ");
                double amount = sc.nextDouble();
                
                try {
                    if(service.validatePhoneNumber(sourceMobileNo)
                            && service.validateTargetMobNo(targetMobileNo)
                            && service.validateAmount(amount))
                        funds = service.fundTransfer(sourceMobileNo, targetMobileNo, amount);
                    
                    System.out.println("Successfully transfered Rs." + amount
                            + " to " + targetMobileNo + ".\n"
                            + "Available balance is Rs. " + funds.getAmount());
                    
                } catch (PhoneNumberException | AmountException| BankException e) {
                    // TODO Auto-generated catch block
                    System.err.println(e);
                }

                break;

            case 4:

                service = new BankServiceImpl();
                System.out.println("Enter your mobile number: ");
                String mobNoDep = sc.next();
                System.out.println("Enter amount that to be deposited: ");
                double amtDep = sc.nextDouble();
                Customer cDep = null;
                try {
                    if(service.validatePhoneNumber(mobNoDep) && service.validateAmount(amtDep))
                        cDep = service.depositAmount(mobNoDep, amtDep);
                    System.out.println("Your current balance is Rs."
                            + cDep.getAmount());
                } catch (AmountException | PhoneNumberException
                        | BankException e) {
                    // TODO Auto-generated catch block
                    System.err.println(e);
                }
                break;

            case 5:

                service = new BankServiceImpl();
                System.out.println("Enter your mobile number: ");
                String mobNoWiDraw = sc.next();
                System.out.println("Enter amount that to be withdrawn: ");
                double amtWiDraw = sc.nextDouble();

                Customer cWD = null;
                try {
                    if(service.validatePhoneNumber(mobNoWiDraw) && service.validateAmount(amtWiDraw))
                        cWD = service.withdrawAmount(mobNoWiDraw, amtWiDraw);
                    System.out.println("Your current balance is Rs. "
                            + cWD.getAmount());
                } catch (AmountException | BankException | PhoneNumberException e) {
                    // TODO Auto-generated catch block
                    System.err.println(e);
                }
                break;

            default:
            case 6:
                break;
            }

        } while (ch!= 6);

        sc.close();
    }

}