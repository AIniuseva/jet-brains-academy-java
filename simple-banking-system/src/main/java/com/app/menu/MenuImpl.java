package com.app.menu;

import com.app.Account;
import com.app.database.Database;

import java.util.Scanner;

public class MenuImpl implements MenuInterface {
    private Account account;
    private Database database;

    @Override
    public void accountCreation() {
        account = new Account();
        database = new Database("jdbc:sqlite:D:\\Java-projects\\jet-brains-academy-java\\simple-banking-system\\src\\main\\resources\\bankdatabase.db");
        database.addNewCard(account.getCardNumber(), String.valueOf(account.getPinCode()));

        System.out.println("Your card has been created");
        System.out.println("Your card number:" + '\n' + account.getCardNumber());
        System.out.println("Your card PIN:" + '\n' + account.getPinCode());
        System.out.println();
    }

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void logIntoAccount() {
        if (account != null) {
            System.out.println("Enter your card number:");

            String cardNumber = scanner.next();
            System.out.println("Enter your PIN:");
            int pinCode = scanner.nextInt();
            if (cardNumber.equals(account.getCardNumber()) && pinCode == account.getPinCode()) {
                System.out.println("You have successfully logged in!");
                accountMenu();
            } else {
                System.out.println("Wrong card number or PIN!");
            }
        }
    }

    @Override
    public void accountMenu() {
        while (true) {
            System.out.println("1. Balance");
            System.out.println("2. Log out");
            System.out.println("0. Exit");
            int menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    System.out.println(account.getBalance());
                    break;
                case 2:
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
