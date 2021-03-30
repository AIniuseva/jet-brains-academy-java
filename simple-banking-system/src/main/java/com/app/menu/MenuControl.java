package com.app.menu;

import java.util.Scanner;

public class MenuControl {
    private final MenuInterface menuInterface;

    public MenuControl(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        while (true) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    menuInterface.accountCreation();
                    break;
                case 2:
                    menuInterface.logIntoAccount();
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
}
