package com.app.service;

import com.app.model.CoffeeMachineContents;
import com.app.model.CoffeeType;

public class CoffeeMachineImplementation extends CoffeeMachineContents {

    public CoffeeMachineImplementation(int availableWater, int availableMilk, int availableCoffeeBeans,
                                       int disposableCups, int moneyVault) {
        super(availableWater, availableMilk, availableCoffeeBeans, disposableCups, moneyVault);
    }


    public void coffeePurchasing(CoffeeType coffeeType) {
        this.setAvailableWater(this.getAvailableWater() - coffeeType.getWaterForOneCup());
        this.setAvailableMilk(this.getAvailableMilk() - coffeeType.getMilkForOneCup());
        this.setAvailableCoffeeBeans(this.getAvailableCoffeeBeans() - coffeeType.getCoffeeBeansForOneCup());
        this.setDisposableCups(this.getDisposableCups() - 1);
        this.setMoneyVault(this.getMoneyVault() + coffeeType.getOneCupPrice());
    }

    public void showCoffeeMachineContent() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water%n", this.getAvailableWater());
        System.out.printf("%d of milk%n", this.getAvailableMilk());
        System.out.printf("%d of coffee beans%n", this.getAvailableCoffeeBeans());
        System.out.printf("%d of disposable cups%n", this.getDisposableCups());
        System.out.printf("%d of money%n", this.getMoneyVault());
    }

    public void coffeeMachineFilling(int waterForFilling, int milkForFilling, int coffeeBeansForFilling, int disposableCupsForFilling) {
        this.setAvailableWater(this.getAvailableWater() + waterForFilling);
        this.setAvailableMilk(this.getAvailableMilk() + milkForFilling);
        this.setAvailableCoffeeBeans(this.getAvailableCoffeeBeans() + coffeeBeansForFilling);
        this.setDisposableCups(this.getDisposableCups() + disposableCupsForFilling);
    }
}
