package com.app.model;

public abstract class CoffeeMachineContents {
    private int availableWater;
    private int availableMilk;
    private int availableCoffeeBeans;
    private int disposableCups;
    private int moneyVault;

    protected CoffeeMachineContents(int availableWater, int availableMilk, int availableCoffeeBeans, int disposableCups, int moneyVault) {
        this.availableWater = availableWater;
        this.availableMilk = availableMilk;
        this.availableCoffeeBeans = availableCoffeeBeans;
        this.disposableCups = disposableCups;
        this.moneyVault = moneyVault;
    }

    public int getAvailableWater() {
        return availableWater;
    }

    public void setAvailableWater(int availableWater) {
        this.availableWater = availableWater;
    }

    public int getAvailableMilk() {
        return availableMilk;
    }

    public void setAvailableMilk(int availableMilk) {
        this.availableMilk = availableMilk;
    }

    public int getAvailableCoffeeBeans() {
        return availableCoffeeBeans;
    }

    public void setAvailableCoffeeBeans(int availableCoffeeBeans) {
        this.availableCoffeeBeans = availableCoffeeBeans;
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setDisposableCups(int disposableCups) {
        this.disposableCups = disposableCups;
    }

    public int getMoneyVault() {
        return moneyVault;
    }

    public void setMoneyVault(int moneyVault) {
        this.moneyVault = moneyVault;
    }
}