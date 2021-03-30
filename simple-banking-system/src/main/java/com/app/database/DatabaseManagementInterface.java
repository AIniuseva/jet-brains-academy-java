package com.app.database;

import com.app.account.Account;

public interface DatabaseManagementInterface {
    void addNewCard(String number, String pin);

    boolean checkPinCode(String number, String pin);

    String getBalance(Account account);

    void addIncome(Account account);

    void doTransfer(Account currentAccount);

    void closeAccount(Account account);
}
