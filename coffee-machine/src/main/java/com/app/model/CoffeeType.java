package com.app.model;

public class CoffeeType {
    private final int waterForOneCup;
    private final int milkForOneCup;
    private final int coffeeBeansForOneCup;
    private final int oneCupPrice;

    public CoffeeType(int waterForOneCup, int milkForOneCup, int coffeeBeansForOneCup, int oneCupPrice) {
        this.waterForOneCup = waterForOneCup;
        this.milkForOneCup = milkForOneCup;
        this.coffeeBeansForOneCup = coffeeBeansForOneCup;
        this.oneCupPrice = oneCupPrice;
    }

    public int getWaterForOneCup() {
        return waterForOneCup;
    }

    public int getMilkForOneCup() {
        return milkForOneCup;
    }

    public int getCoffeeBeansForOneCup() {
        return coffeeBeansForOneCup;
    }

    public int getOneCupPrice() {
        return oneCupPrice;
    }
}
