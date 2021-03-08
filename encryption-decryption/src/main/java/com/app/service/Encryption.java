package com.app.service;

public class Encryption implements DecEncStrategy {
    @Override
    public StringBuilder messageUnicodeChanging(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            encryptedMessage.append((char) (message.charAt(i) + key));
        }
        return encryptedMessage;
    }

    @Override
    public StringBuilder messageShiftChanging(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (String.valueOf(message.charAt(i)).matches("[!.,?\\s+]")) {
                encryptedMessage.append(message.charAt(i));
            } else if (String.valueOf(message.charAt(i)).matches("[a-z]") && message.charAt(i) + key > 'z') {
                encryptedMessage.append((char) ('a' - 1 + key - ('z' - message.charAt(i))));
            } else if (String.valueOf(message.charAt(i)).matches("[A-Z]") && message.charAt(i) + key > 'Z') {
                encryptedMessage.append((char) ('A' - 1 + key - ('Z' - message.charAt(i))));
            } else {
                encryptedMessage.append((char) (message.charAt(i) + key));
            }
        }
        return encryptedMessage;
    }

}


