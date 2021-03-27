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

    public int getBalance(Account account) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);

        String selectBalance = "SELECT balance FROM card WHERE number=(?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(selectBalance)) {
                preparedStatement.setString(1, account.getCardNumber());
                ResultSet table = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public void addIncome(Account account) {
        Scanner scanner = new Scanner(System.in);
        int income = scanner.nextInt();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(databaseUrl);

        String selectBalance = "UPDATE card SET balance = (?) WHERE number=(?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(selectBalance)) {
                preparedStatement.setInt(1, income);
                preparedStatement.setString(2, account.getCardNumber());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

