package com.app.account;

import java.util.Random;

public class Account {
    private final String cardNumber;
    private final String pinCode;

    public Account() {
        this.cardNumber = generateCardNumber();
        this.pinCode = String.valueOf(random.nextInt(9999 - 1000 + 1) + 1000);
    }

    public Account(String cardNumber, String pinCode) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    private final Random random = new Random();

    public String generateCardNumber() {
        StringBuilder cardNum = new StringBuilder();
        cardNum.append(4);
        for (int i = 1; i < 15; i++) {
            if (i < 6) {
                cardNum.append(0);
            } else {
                cardNum.append(random.nextInt(9));
            }
        }
        cardNum.append(luhnAlgorithmBuilder(cardNum));
        return String.valueOf(cardNum);
    }

    public int luhnAlgorithmBuilder(StringBuilder cardNum) {
        int controlNumber = 0;

        for (int i = cardNum.length() - 1; i > -1; i--) {
            if (i % 2 == 0) {
                int number = (Integer.parseInt(String.valueOf(cardNum.charAt(i))) * 2);
                if (number > 9) {
                    controlNumber += number - 9;
                } else {
                    controlNumber += number;
                }
            } else {
                controlNumber += Integer.parseInt(String.valueOf(cardNum.charAt(i)));
            }
        }
        return controlNumber % 10 == 0 ? 0 : 10 - controlNumber % 10;
    }
}