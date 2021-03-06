package com.app.service;

import com.app.service.DecEncStrategy;

public class Decryption implements DecEncStrategy {
    @Override
    public StringBuilder messageUnicodeChanging(String message, int key) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            decryptedMessage.append((char) (message.charAt(i) - key));
        }
        return decryptedMessage;
    }

    @Override
    public StringBuilder messageShiftChanging(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (String.valueOf(message.charAt(i)).matches("[!.,?\\s+]")) {
                encryptedMessage.append(message.charAt(i));
            } else if (String.valueOf(message.charAt(i)).matches("[a-z]") && message.charAt(i) - key < 'a') {
                encryptedMessage.append((char) ('z' + 1 - key + (message.charAt(i) - 'a')));
            } else if (String.valueOf(message.charAt(i)).matches("[A-Z]") && message.charAt(i) - key < 'A') {
                encryptedMessage.append((char) ('Z' + 1 - key + (message.charAt(i) - 'A')));
            } else {
                encryptedMessage.append((char) (message.charAt(i) - key));
            }
        }
        return encryptedMessage;
    }
}
