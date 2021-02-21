package com.app;

import com.app.model.CoffeeType;
import com.app.service.CoffeeMachineImplementation;

import java.util.Scanner;

public class CoffeeMachine {
    private static final CoffeeMachineImplementation coffeeMachine = new CoffeeMachineImplementation
            (400, 540, 120, 9, 550);
    private static final CoffeeType ESPRESSO = new CoffeeType(250, 0, 16, 4);
    private static final CoffeeType LATTE = new CoffeeType(350, 75, 20, 7);
    private static final CoffeeType CAPPUCCINO = new CoffeeType(200, 100, 12, 6);

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            System.out.println("Write action (buy, fill, take, remaining, exit)");

            String chosenAction = scanner.nextLine();
            switch (chosenAction) {
                case "buy":
                    menuForBuying();
                    break;
                case "fill":
                    menuForFilling();
                    break;
                case "take":
                    menuForTaking();
                    break;
                case "remaining":
                    coffeeMachine.showCoffeeMachineContent();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown action!");
                    break;
            }
            System.out.println();
        }
    }

    private static void menuForBuying() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                coffeeMachine.coffeePurchasing(ESPRESSO);
                break;
            case "2":
                coffeeMachine.coffeePurchasing(LATTE);
                break;
            case "3":
                coffeeMachine.coffeePurchasing(CAPPUCCINO);
                break;
            case "back":
                return;
            default:
                System.out.println("Wrong statement!");
                break;
        }
    }

    private static void menuForFilling() {
        System.out.println("Write how many ml of water do you want to add:");
        int waterForFilling = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int milkForFilling = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int coffeeBeansForFilling = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int disposableCupsForFilling = scanner.nextInt();
        scanner.nextLine();
        coffeeMachine.coffeeMachineFilling(waterForFilling, milkForFilling, coffeeBeansForFilling, disposableCupsForFilling);
    }

    private static void menuForTaking() {
        System.out.printf("I gave you $%d%n", coffeeMachine.getMoneyVault());
        coffeeMachine.setMoneyVault(0);
    }
}
