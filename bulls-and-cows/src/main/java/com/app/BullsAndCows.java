package com.app;

import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Random random = new Random();
        int randomNumberLength;

        do {
            System.out.println("Please, enter the secret code's length:");
            randomNumberLength = scanner.nextInt();
            if (randomNumberLength > 10) {
                System.out.printf("Error: can't generate a secret number with a length of " +
                        "%d because there aren't enough unique digits.%n", randomNumberLength);
            }
        } while (randomNumberLength > 10);
        scanner.nextLine();

        int randomRange = (int) (Math.pow(10, --randomNumberLength));

        String secretCode = null;
        boolean isContainsTheSameNumber = true;

        //unique number generator:
        while (isContainsTheSameNumber) {
            secretCode = String.valueOf(random.nextInt(((randomRange * 10) - 1) - randomRange + 1) + randomRange);

            if (secretCode.length() == 1) {
                break;
            }
            for (int i = 0; i < secretCode.length() - 1; i++) {
                StringBuilder secretCodeSave = new StringBuilder(secretCode);
                char numberForCheck = secretCode.charAt(i);
                secretCodeSave.deleteCharAt(i);
                isContainsTheSameNumber = secretCodeSave.toString().contains(String.valueOf(numberForCheck));

                if (isContainsTheSameNumber) {
                    break;
                }
            }
        }

        String playerGuess = null;
        int turn = 1;

        System.out.println("Okay, let's start a game!");

        while (!secretCode.equals(playerGuess)) {
            System.out.printf("Turn %d:%n", turn);
            playerGuess = scanner.nextLine();
            resultChecker(playerGuess, secretCode);
            turn++;
        }

        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static String resultChecker(String guess, String secretCode) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (secretCode.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }

        if (bulls > 0 && cows == 0) {
            System.out.println("Grade: " + bulls + " bulls(s).");
            return secretCode;
        } else if (cows > 0 && bulls == 0) {
            System.out.println("Grade: " + cows + " cow(s).");
        } else if (cows > 0 && bulls > 0) {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");
        } else {
            System.out.println("None.");
        }
        return guess;
    }
}

