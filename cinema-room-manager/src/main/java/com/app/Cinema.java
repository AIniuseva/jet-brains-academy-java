package com.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {
    public static final Scanner scanner = new Scanner(System.in);
    public static final String INPUT_MISMATCH_MESSAGE = "Error! Input the numbers.";
    private static boolean error;

    public static void main(String[] args) {
        int totalRows = 0;
        int totalSeats = 0;

        do {
            error = false;
            try {
                System.out.println("Enter the number of rows:");
                totalRows = scanner.nextInt();
                System.out.println("Enter the number of seats in each row:");
                totalSeats = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(INPUT_MISMATCH_MESSAGE);
                error = true;
            }
            scanner.nextLine();
        } while (error);

        String[][] cinema = cinemaCreation(totalRows, totalSeats);

        int totalNumberOfSeats = totalRows * totalSeats;
        int numOfPurchasedTickets = 0;
        double percentageOfPurchasedSeats = 0;
        int currentIncome = 0;
        int totalIncome = calculateTotalIncome(totalRows, totalSeats);

        int menuChoice = 1;
        while (menuChoice != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            do {
                error = false;
                try {
                    menuChoice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(INPUT_MISMATCH_MESSAGE);
                    error = true;
                }
                scanner.nextLine();
            } while (error);

            switch (menuChoice) {
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    currentIncome += buyTicket(cinema);
                    numOfPurchasedTickets++;
                    percentageOfPurchasedSeats = calculatePercentageOfPurchasedSeats(totalNumberOfSeats, numOfPurchasedTickets);
                    break;
                case 3:
                    printStatistics(numOfPurchasedTickets, percentageOfPurchasedSeats, currentIncome, totalIncome);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Wrong statement, try again.");
                    break;
            }
        }
    }

    private static String[][] cinemaCreation(int rows, int seats) {
        String[][] cinema = new String[rows + 1][seats + 1];
        int num = 1;

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = String.valueOf(num++);
                } else if (j == 0) {
                    cinema[i][j] = String.valueOf(i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }
        return cinema;
    }

    private static void printCinema(String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int buyTicket(String[][] cinema) {
        int rowNumber = 0;
        int seatNumber = 0;

        while (true) {
            do {
                error = false;
                try {
                    System.out.println("Enter a row number:");
                    rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNumber = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(INPUT_MISMATCH_MESSAGE);
                    error = true;
                }
                scanner.nextLine();
            } while (error);

            if ((cinema.length - 1) < rowNumber || (cinema[0].length - 1) < seatNumber) {
                System.out.println("Wrong input!");
            } else if (cinema[rowNumber][seatNumber].equals("B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                cinema[rowNumber][seatNumber] = "B";
                int ticketPrice = calculateTicketPrice(cinema, rowNumber);
                System.out.println("Ticket price: $" + ticketPrice + '\n');

                return ticketPrice;
            }
        }
    }

    private static int calculateTicketPrice(String[][] cinema, int chosenRow) {
        int totalNumberOfSeats = (cinema.length - 1) * (cinema[0].length - 1);
        int ticketPrice;

        if (totalNumberOfSeats <= 60) {
            ticketPrice = 10;
        } else {
            ticketPrice = chosenRow <= (cinema.length - 1) / 2 ? 10 : 8;
        }
        return ticketPrice;
    }

    private static void printStatistics(int numOfPurchasedTickets, double percentageOfPurchasedSeats, int currentIncome, int totalIncome) {
        System.out.printf("Number of purchased tickets: %d%n", numOfPurchasedTickets);
        System.out.printf("Percentage: %.2f", percentageOfPurchasedSeats);
        System.out.print("%\n");
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n%n", totalIncome);
    }

    private static int calculateTotalIncome(int rows, int seats) {
        int totalNumberOfSeats = rows * seats;

        if (totalNumberOfSeats <= 60) {
            return totalNumberOfSeats * 10;
        }

        return rows % 2 != 0 ? (rows / 2 * seats * 10) + ((rows / 2 + 1) * seats * 8)
                : (rows / 2 * seats * 10) + (rows / 2 * seats * 8);
    }

    private static double calculatePercentageOfPurchasedSeats(int totalNumberOfSeats, int numOfPurchasedTickets) {
        return (double) numOfPurchasedTickets * 100 / (double) totalNumberOfSeats;
    }
}