package com.app;

import java.util.Random;

class Account {
    private final String cardNumber;
    private final int pinCode;

    private long balance;

    public Account() {
        this.cardNumber = generateCardNumber();
        this.pinCode = random.nextInt((9999 - 1000 + 1) + 1000);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public long getBalance() {
        return balance;
    }

    private final Random random = new Random();

    public String generateCardNumber() {
        StringBuilder cardNum = new StringBuilder();
        cardNum.append(4);
        for (int i = 1; i < 16; i++) {
            if (i < 6) {
                cardNum.append(0);
            } else {
                cardNum.append(random.nextInt(9));
            }
        }
        return String.valueOf(cardNum);
    }
}