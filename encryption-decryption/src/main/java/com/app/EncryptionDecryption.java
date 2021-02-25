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
                if ((int) message.charAt(i) + key < (int) 'z') {
                    encryptedMessage.append((char) ((int) message.charAt(i) + key));
                } else {
                    encryptedMessage.append((char) ((int) 'a' - 1 + key - ((int) 'z' - (int) message.charAt(i))));
                }
            } else if (String.valueOf(message.charAt(i)).matches("[A-Z]")) {
                if ((int) message.charAt(i) + key < (int) 'Z') {
                    encryptedMessage.append((char) ((int) message.charAt(i) + key));
                } else {
                    encryptedMessage.append((char) ((int) 'A' - 1 + key - ((int) 'Z' - (int) message.charAt(i))));
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
                encryptedMessage.append((char) ((int) 'z' - ((int) message.charAt(i) - (int) 'a')));
            } else if (String.valueOf(message.charAt(i)).matches("[A-Z]")) {
                encryptedMessage.append((char) ((int) 'Z' - ((int) message.charAt(i) - (int) 'A')));
            } else {
                encryptedMessage.append(message.charAt(i));
            }
        }
        return encryptedMessage;
    }
}
