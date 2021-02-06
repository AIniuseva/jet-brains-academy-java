package com.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    private static final char X = 'X';
    private static final char O = 'O';
    private static final String GAME_NOT_FINISHED_RESULT = "Game not finished";
    private static final String DRAW_RESULT = "Draw";
    private static final String X_WINS_RESULT = "X wins";
    private static final String O_WINS_RESULT = "O wins";
    private static final String IMPOSSIBLE_RESULT = "Impossible";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;

        System.out.print("Enter cells: ");
        input = scanner.nextLine();
        char[][] ticTacToeFiled = new char[3][3];

        int k = 0;

        for (int i = 0; i < ticTacToeFiled.length; i++) {
            for (int j = 0; j < ticTacToeFiled[0].length; j++) {
                ticTacToeFiled[i][j] = input.charAt(k);
                k++;
            }
        }

        printTicTacToeGameField(ticTacToeFiled);

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

            if (ticTacToeFiled[xCoord - 1][oCoord - 1] != X && ticTacToeFiled[xCoord - 1][oCoord - 1] != O) {
                ticTacToeFiled[xCoord - 1][oCoord - 1] = X;
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
        printTicTacToeGameField(ticTacToeFiled);

        System.out.println(gameResultChecking(ticTacToeFiled, input));
    }

    private static void printTicTacToeGameField(char[][] gameField) {
        System.out.println("---------");
        for (int i = 0; i < gameField.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < gameField[0].length; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String gameResultChecking(char[][] gameField, String input) {
        if (impossibleSituationResultChecking(gameField)) {
            return IMPOSSIBLE_RESULT;
        } else {
            return ResultChecking(gameField, input);
        }
    }

    private static boolean impossibleSituationResultChecking(char[][] gameField) {
        int totalXs = 0;
        int totalOs = 0;

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if (gameField[i][j] == X) {
                    totalXs++;
                } else if (gameField[i][j] == O) {
                    totalOs++;
                }
            }
        }
        return totalXs - totalOs >= 2 || totalOs - totalXs >= 2 || totalOs == 0 || totalXs == 0;
    }

    private static String ResultChecking(char[][] gameField, String input) {
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

        if (xHor == 3 && oHor == 3 || xVert == 3 && oVert == 3) {
            return IMPOSSIBLE_RESULT;
        } else if (xHor == 3 || xVert == 3) {
            return X_WINS_RESULT;
        } else if (oHor == 3 || oVert == 3) {
            return O_WINS_RESULT;
        } else if (gameField[0][0] == X && gameField[1][1] == X && gameField[2][2] == X ||
                gameField[2][0] == X && gameField[1][1] == X && gameField[0][2] == X) {
            return X_WINS_RESULT;
        } else if (gameField[0][0] == O && gameField[1][1] == O && gameField[2][2] == O ||
                gameField[2][0] == O && gameField[1][1] == O && gameField[0][2] == O) {
            return O_WINS_RESULT;
        } else if ((input.contains(" ") || input.contains("_"))) {
            return GAME_NOT_FINISHED_RESULT;
        } else return DRAW_RESULT;
    }
}