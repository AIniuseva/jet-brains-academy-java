package com.app;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;
        input = scanner.nextLine();
        char[][] ticTacToeFiled = new char[3][3];

        int k = 0;

        for (int i = 0; i < ticTacToeFiled.length; i++) {
            for (int j = 0; j < ticTacToeFiled[0].length; j++) {
                ticTacToeFiled[i][j] = input.charAt(k);
                k++;
            }
        }

        System.out.println("---------");
        for (int i = 0; i < ticTacToeFiled.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < ticTacToeFiled[0].length; j++) {
                System.out.print(ticTacToeFiled[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}