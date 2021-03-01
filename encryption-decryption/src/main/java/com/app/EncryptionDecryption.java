package com.app;

import com.app.service.Decryption;
import com.app.service.Encryption;

import java.util.Scanner;

public class EncryptionDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message;
        int key;
        String choice = scanner.nextLine();

        switch (choice) {
            case "enc":
                message = scanner.nextLine();
                Encryption encryption = new Encryption(message);
                key = scanner.nextInt();
                System.out.println(encryption.keyEncryptor(key));
                break;
            case "dec":
                message = scanner.nextLine();
                Decryption decryption = new Decryption(message);
                key = scanner.nextInt();
                System.out.println(decryption.keyDecryptor(key));
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }
}
