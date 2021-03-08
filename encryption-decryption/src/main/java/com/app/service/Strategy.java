package com.app.service;

public class Strategy {
    private final DecEncStrategy decEncStrategy;
    private final int key;
    private final String alg;

    public Strategy(DecEncStrategy decEncStrategy, int key, String alg) {
        this.decEncStrategy = decEncStrategy;
        this.key = key;
        this.alg = alg;
    }

    public StringBuilder getChangedMessage(String message) {
        if (alg.equals("unicode")) {
            return decEncStrategy.messageUnicodeChanging(message, key);
        } else if (alg.equals("shift")) {
            return decEncStrategy.messageShiftChanging(message, key);
        }
        return null;
    }
}
