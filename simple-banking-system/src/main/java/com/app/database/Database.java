package com.app.database;

import com.app.Account;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

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

        String select = "SELECT pin FROM card WHERE number=(?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(select)) {
                preparedStatement.setString(1,number);
                ResultSet table = preparedStatement.executeQuery();
                String realPin = table.getString("pin");
                System.out.println(pin);

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
}

