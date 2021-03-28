package com.app.menu;

import com.app.account.Account;
import com.app.database.DatabaseManagement;

import java.util.Scanner;

public class MenuImpl implements MenuInterface {
    private final DatabaseManagement databaseManagement = new DatabaseManagement
            ("jdbc:sqlite:D:\\Java-projects\\jet-brains-academy-java\\simple-banking-system\\src\\main\\resources\\bankdatabase.db");

    private Account currentAccount;

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    @Override
    public void accountCreation() {
        Account account = new Account();
        databaseManagement.addNewCard(account.getCardNumber(), String.valueOf(account.getPinCode()));

        System.out.println("Your card has been created");
        System.out.println("Your card number:" + '\n' + account.getCardNumber());
        System.out.println("Your card PIN:" + '\n' + account.getPinCode());
        System.out.println();
    }

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void logIntoAccount() {
        System.out.println("Enter your card number:");

        String cardNumber = scanner.next();
        System.out.println("Enter your PIN:");
        String pinCode = scanner.next();

        if (databaseManagement.checkPinCode(cardNumber,pinCode)){
            System.out.println("You have successfully logged in!");
            this.currentAccount = new Account(cardNumber,pinCode);
            accountMenu();
        } else {
            System.out.println("Wrong card number or PIN!");
        }

    }

    @Override
    public void accountMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. Balance\n" +
                    "2. Add income\n" +
                    "3. Do transfer\n" +
                    "4. Close account\n" +
                    "5. Log out\n" +
                    "0. Exit");
            int menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    System.out.println("Balance: " + databaseManagement.getBalance(currentAccount));
                    break;
                case 2:
                    databaseManagement.addIncome(currentAccount);
                    break;
                case 3:
                    databaseManagement.doTransfer(currentAccount);
                    break;
                case 4:
                    databaseManagement.closeAccount(currentAccount);
                    return;
                case 5:
                    setCurrentAccount(null);
                    return;
                case 0:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Error.");
                    break;
            }
        }
    }
}
