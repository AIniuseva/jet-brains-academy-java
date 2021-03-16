package com.app;

import java.util.Scanner;

public class SimpleBankingSystem {
    private static Account account;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    accountCreation();
                    break;
                case 2:
                    logIntoAccount();
                    break;
                case 0:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Error.");
                    break;
            }
        }
    }

    private static void accountCreation() {
        account = new Account();
        System.out.println("Your card has been created");
        System.out.println("Your card number:" + '\n' + account.getCardNumber());
        System.out.println("Your card PIN:" + '\n' + account.getPinCode());
        System.out.println();
    }

    private static void logIntoAccount() {
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

    private static void accountMenu() {
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
