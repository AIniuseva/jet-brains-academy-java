package com.app;

import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretCode = random.nextInt(9999 - 1000 + 1) + 1000;
        String guess = scanner.nextLine();
        System.out.println(secretCode);

        System.out.println(grader(guess, String.valueOf(secretCode)));
    }

    private static String grader(String guess, String secretCode) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boolean equal = secretCode.charAt(i) == guess.charAt(j);
                if (i == j && equal) {
                    bulls++;
                } else if (equal) {
                    cows++;
                }
            }
        }

        if (bulls > 0 && cows == 0) {
            return "Grade: " + bulls + " bulls(s). The secret code is " + secretCode;
        } else if (cows > 0 && bulls == 0) {
            return "Grade: " + cows + " cow(s). The secret code is " + secretCode;
        } else if (bulls > 0 && cows > 0) {
            return "Grade: " + bulls + " bull(s) and " + cows + " cow(s). The secret code is " + secretCode;
        } else {
            return "None. The secret code is " + secretCode;
        }
    }
}
