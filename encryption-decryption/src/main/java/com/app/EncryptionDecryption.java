package com.app;

import java.util.Scanner;

public class EncryptionDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = scanner.nextInt();

        System.out.println(keyEncryptor(message, key));
    }

    private static StringBuilder keyEncryptor(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (String.valueOf(message.charAt(i)).matches("[a-z]")) {
                if ((int) message.charAt(i) + key < 'z') {
                    encryptedMessage.append((char) (message.charAt(i) + key));
                } else {
                    encryptedMessage.append((char) ('a' - 1 + key - ('z' - message.charAt(i))));
                }
            } else if (String.valueOf(message.charAt(i)).matches("[A-Z]")) {
                if ((int) message.charAt(i) + key < 'Z') {
                    encryptedMessage.append((char) (message.charAt(i) + key));
                } else {
                    encryptedMessage.append((char) ('A' - 1 + key - ('Z' - message.charAt(i))));
                }
            } else {
                encryptedMessage.append(message.charAt(i));
            }

        }
        return encryptedMessage;
    }

    private static StringBuilder primitiveEncryptor(String message) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (String.valueOf(message.charAt(i)).matches("[a-z]")) {
                encryptedMessage.append((char) ('z' - (message.charAt(i) - 'a')));
            } else if (String.valueOf(message.charAt(i)).matches("[A-Z]")) {
                encryptedMessage.append((char) ('Z' - (message.charAt(i) - 'A')));
            } else {
                encryptedMessage.append(message.charAt(i));
            }
        }
        return encryptedMessage;
    }
}
