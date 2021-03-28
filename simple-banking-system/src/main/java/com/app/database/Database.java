package com.app.database;

import com.app.account.Account;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Scanner;

public class Database {
    private final String databaseUrl;

    public Database(String databaseUrl) {
        this.databaseUrl = databaseUrl;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) { //TODO: change to prepare statement
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY ," +
                        "number TEXT NOT NULL," +
                        "pin TEXT NOT NULL," +
                        "balance INTEGER DEFAULT 0);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewCard(String number, String pin) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(this.databaseUrl);

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("INSERT INTO card (number,pin) VALUES (" + number + "," + pin + ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPinCode(String number, String pin) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);

        String selectPin = "SELECT pin FROM card WHERE number=(?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(selectPin)) {
                preparedStatement.setString(1, number);
                ResultSet table = preparedStatement.executeQuery();
                String realPin = table.getString("pin");

                return pin.equals(realPin);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getBalance(Account account) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);

        String selectBalance = "SELECT balance FROM card WHERE number=(?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(selectBalance)) {
                preparedStatement.setString(1, account.getCardNumber());
                ResultSet table = preparedStatement.executeQuery();

                return table.getString("balance");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    private final Scanner scanner = new Scanner(System.in);

    public void addIncome(Account account) {
        System.out.println("Enter income:");
        int income = scanner.nextInt();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);

        String updateBalance = "UPDATE card SET balance = balance + (?) WHERE number=(?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(updateBalance)) {
                preparedStatement.setInt(1, income);
                preparedStatement.setString(2, account.getCardNumber());
                preparedStatement.executeUpdate();
                System.out.println("Income was added!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doTransfer(Account currentAccount) {
        System.out.println("Transfer\n" +
                "Enter card number:");
        String numberToTransfer = scanner.next();

        if (currentAccount.getCardNumber().equals(numberToTransfer)) {
            System.out.println("You can't transfer money to the same account!");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        int moneyToTransfer = scanner.nextInt();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);

        String updateBalance = "UPDATE card SET balance = balance + (?) WHERE number = (?)";
        String selectBalance = "SELECT balance FROM card WHERE number = (?)";
        String checkingIfNumberExist = "SELECT number FROM card WHERE number = (?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement updateStatement = con.prepareStatement(updateBalance)) {
                //TODO: add luhn algorithm checker
                PreparedStatement selectStatement = con.prepareStatement(selectBalance);
                selectStatement.setString(1, currentAccount.getCardNumber());
                ResultSet table = selectStatement.executeQuery();
                String balance = table.getString("balance");

                selectStatement = con.prepareStatement(checkingIfNumberExist);
                selectStatement.setString(1, numberToTransfer);
                table = selectStatement.executeQuery();
                table.getString("number"); //if number does not exist SQLException will appear

                if (Integer.parseInt(balance) < moneyToTransfer) {
                    System.out.println("Not enough money!");
                    return;
                }
                con.setAutoCommit(false);

                updateStatement.setInt(1, -moneyToTransfer);
                updateStatement.setString(2, currentAccount.getCardNumber());
                updateStatement.executeUpdate();

                updateStatement.setInt(1, moneyToTransfer);
                updateStatement.setString(2, numberToTransfer);
                updateStatement.executeUpdate();

                con.commit();
                System.out.println("Success!");
            } catch (SQLException e) {
                System.out.println("Such a card does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(Account currentAccount){
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);

        String deleteCard = "DELETE FROM card WHERE number = (?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteCard)) {
                preparedStatement.setString(1, currentAccount.getCardNumber());
                preparedStatement.executeUpdate();
                System.out.println("The account has been closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

