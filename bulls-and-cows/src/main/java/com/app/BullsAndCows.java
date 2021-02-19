package com.app;

import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final char[] symbolsForSecretCode = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x',
            'y', 'z'};

    public static void main(String[] args) {
        int randomNumberLength = 0;
        int numberOfPossibleCharacters = 0;

        String input = null;
        boolean isError;
        do {
            isError = false;
            try {
                System.out.println("Input the length of the secret code:");
                input = scanner.nextLine();
                randomNumberLength = Integer.parseInt(input);

                System.out.println("Input the number of possible symbols in the code:");
                input = scanner.nextLine();
                numberOfPossibleCharacters = Integer.parseInt(input);

                if (randomNumberLength > numberOfPossibleCharacters || randomNumberLength == 0) {
                    System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n",
                            randomNumberLength, numberOfPossibleCharacters);
                    isError = true;
                } else if (numberOfPossibleCharacters > 36) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    isError = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + input + " isn't a valid number.");
                isError = true;
            }
        } while (isError);

        final String secretCode = uniqueNumberGenerator(randomNumberLength, numberOfPossibleCharacters);

        System.out.printf("The secret is prepared: %s ", "*".repeat(randomNumberLength));
        System.out.printf(numberOfPossibleCharacters > 10 ? "(0-9, a-%s)%n" : "(0-9)%n",
                symbolsForSecretCode[numberOfPossibleCharacters - 1]);

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

    private static String uniqueNumberGenerator(int randomNumberLength, int numberOfPossibleCharacters) {
        char[] secretCode = new char[randomNumberLength];
        boolean isContainsTheSameSymbol = true;

        while (isContainsTheSameSymbol) {
            for (int i = 0; i < randomNumberLength; i++) {
                secretCode[i] = symbolsForSecretCode[random.nextInt(numberOfPossibleCharacters)];
            }

            if (secretCode.length == 1) {
                break;
            }

            for (int i = 0; i < secretCode.length - 1; i++) {
                StringBuilder secretCodeSave = new StringBuilder(String.valueOf(secretCode));
                char symbolForCheck = secretCode[i];
                secretCodeSave.deleteCharAt(i);
                isContainsTheSameSymbol = secretCodeSave.toString().contains(String.valueOf(symbolForCheck));

                if (isContainsTheSameSymbol) {
                    break;
                }
            }
        }
        return String.valueOf(secretCode);
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