package com.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char X = 'X';
    private static final char O = 'O';
    private static final String DRAW_RESULT = "Draw";
    private static final String X_WINS_RESULT = "X wins";
    private static final String O_WINS_RESULT = "O wins";

    public static void main(String[] args) {
        char[][] ticTacToeField = new char[3][3];

        for (int i = 0; i < ticTacToeField.length; i++) {
            for (int j = 0; j < ticTacToeField[0].length; j++) {
                ticTacToeField[i][j] = ' ';
            }
        }

        boolean gameStart = true;

        while (gameStart) {
            printTicTacToeGameField(ticTacToeField);
            coordinatesEntering(ticTacToeField, X);
            gameStart = checkingEmptySpaces(ticTacToeField);

            if (!gameStart || !resultChecking(ticTacToeField).equals(DRAW_RESULT)) {
                break;
            }
            printTicTacToeGameField(ticTacToeField);
            coordinatesEntering(ticTacToeField, O);

            if (!resultChecking(ticTacToeField).equals(DRAW_RESULT)) {
                break;
            }
            gameStart = checkingEmptySpaces(ticTacToeField);
        }
        printTicTacToeGameField(ticTacToeField);
        System.out.println(resultChecking(ticTacToeField));
    }

    private static boolean checkingEmptySpaces(char[][] gameField) {
        boolean gameStart = true;

        for (char[] chars : gameField) {
            for (char aChar : chars) {
                if (aChar == ' ') {
                    gameStart = true;
                    break;
                } else {
                    gameStart = false;
                }
            }
            if (gameStart) {
                break;
            }
        }
        return gameStart;
    }

    private static void coordinatesEntering(char[][] gameField, char playerSide) {
        int xCoord = 0;
        int oCoord = 0;

        while (true) {
            boolean isError;
            do {
                isError = false;
                try {
                    System.out.print("Enter the coordinates: ");
                    xCoord = scanner.nextInt();
                    oCoord = scanner.nextInt();
                    if (xCoord > 3 || oCoord > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        isError = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    isError = true;
                }
                scanner.nextLine();
            } while (isError);

            if (gameField[xCoord - 1][oCoord - 1] != X && gameField[xCoord - 1][oCoord - 1] != O) {
                gameField[xCoord - 1][oCoord - 1] = playerSide;
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    private static void printTicTacToeGameField(char[][] gameField) {
        System.out.println("---------");
        for (char[] chars : gameField) {
            System.out.print("| ");
            for (int j = 0; j < gameField[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String resultChecking(char[][] gameField) {
        int xHor = 0;
        int oHor = 0;
        int xVert = 0;
        int oVert = 0;

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if (gameField[i][j] == X && xHor != 3) {
                    xHor++;
                } else if (gameField[i][j] == O && oHor != 3) {
                    oHor++;
                }
                if (gameField[j][i] == X && xVert != 3) {
                    xVert++;
                } else if (gameField[j][i] == O && oVert != 3) {
                    oVert++;
                }
            }
            if (xHor < 3) { xHor = 0; }
            if (oHor < 3) { oHor = 0; }
            if (xVert < 3) { xVert = 0; }
            if (oVert < 3) { oVert = 0; }
        }

        if (xHor == 3 || xVert == 3 || (gameField[0][0] == X && gameField[1][1] == X && gameField[2][2] == X ||
                gameField[2][0] == X && gameField[1][1] == X && gameField[0][2] == X)) {
            return X_WINS_RESULT;
        } else if (oHor == 3 || oVert == 3 || (gameField[0][0] == O && gameField[1][1] == O && gameField[2][2] == O ||
                gameField[2][0] == O && gameField[1][1] == O && gameField[0][2] == O)) {
            return O_WINS_RESULT;
        } else return DRAW_RESULT;
    }
}