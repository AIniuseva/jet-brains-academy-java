package com.app;

import java.util.Scanner;

public class CoffeeMachine {
    private static final int WATER_FOR_ONE_CUP = 200;
    private static final int MILK_FOR_ONE_CUP = 50;
    private static final int COFFEE_BEANS_FOR_ONE_CUP = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water the coffee machine has:");
        int waterAvailable = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milkAvailable = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeansAvailable = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        int coffeeCups = scanner.nextInt();

        int neededWaterForCoffeeCups = WATER_FOR_ONE_CUP * coffeeCups;
        int neededMilkForCoffeeCups = MILK_FOR_ONE_CUP * coffeeCups;
        int neededCoffeeBeansForCoffeeCups = COFFEE_BEANS_FOR_ONE_CUP * coffeeCups;

        if (neededWaterForCoffeeCups <= waterAvailable && neededMilkForCoffeeCups <= milkAvailable
                && neededCoffeeBeansForCoffeeCups <= coffeeBeansAvailable) {
            System.out.print("Yes, I can make that amount of coffee");

            waterAvailable -= neededWaterForCoffeeCups;
            milkAvailable -= neededMilkForCoffeeCups;
            coffeeBeansAvailable -= neededCoffeeBeansForCoffeeCups;
            int morePossibleCups = calculatePossibleCups(waterAvailable, milkAvailable, coffeeBeansAvailable);
            if (morePossibleCups > 0) {
                System.out.printf(" (and even %d more than that)%n", morePossibleCups);
            }
        } else {
            int possibleCups = calculatePossibleCups(waterAvailable, milkAvailable, coffeeBeansAvailable);
            System.out.printf("No, I can make only %d cup(s) of coffee", possibleCups);
        }
    }

    private static int calculatePossibleCups(int waterAvailable, int milkAvailable, int coffeeBeansAvailable) {
        return Math.min(Math.min(waterAvailable / WATER_FOR_ONE_CUP, milkAvailable / MILK_FOR_ONE_CUP), coffeeBeansAvailable / COFFEE_BEANS_FOR_ONE_CUP);
    }
}
