package com.app;

import java.util.Scanner;

public class Cinema {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int totalRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int totalSeats = scanner.nextInt();

        String[][] cinema = cinemaCreation(totalRows, totalSeats);

        int menuChoice = 1;
        while (menuChoice != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");

            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Wrong statement, try again.");
                    break;
            }
        }
    }

    public static String[][] cinemaCreation(int rows, int seats) {

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

    public static void printCinema(String[][] cinema) {
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

    public static void buyTicket(String[][] cinema) {
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();

        cinema[rowNumber][seatNumber] = "B";
        int ticketPrice = calculateTicketPrice(cinema, rowNumber);

        System.out.println("Ticket price: $" + ticketPrice + '\n');
    }

    public static int calculateTicketPrice(String[][] cinema, int chosenRow) {
        int totalNumberOfSeats = (cinema.length - 1) * (cinema[0].length - 1);
        int ticketPrice;

        if (totalNumberOfSeats <= 60) {
            ticketPrice = 10;
        } else {
            ticketPrice = (chosenRow <= (cinema.length - 1) / 2) ? 10 : 8;
        }
        return ticketPrice;
    }
}