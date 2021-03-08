package com.app;

import com.app.service.Decryption;
import com.app.service.Encryption;
import com.app.service.Strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EncryptionDecryption {
    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String initialData = null;
        String fileWithData = null;
        String fileForSaving = null;
        String algorithm = null;
        boolean fileSaving = false;
        boolean fileReading = false;

        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                mode = args[i + 1];
            } else if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            } else if ("-data".equals(args[i])) {
                initialData = args[i + 1];
            } else if ("-in".equals(args[i])) {
                fileWithData = args[i + 1];
                fileReading = true;
            } else if ("-out".equals(args[i])) {
                fileForSaving = args[i + 1];
                fileSaving = true;
            } else if ("-alg".equals(args[i])) {
                algorithm = args[i + 1];
            }
        }

        if (fileReading) {
            File file = new File(fileWithData);
            try (Scanner scanner = new Scanner(file)) {
                initialData = String.valueOf(scanner.nextLine());
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + fileWithData);
            }
        }

        switch (mode) {
            case "enc":
                Strategy encStrategy = new Strategy(new Encryption(), key, algorithm);
                if (fileSaving) {
                    savingToFile(String.valueOf(encStrategy.getChangedMessage(initialData)), fileForSaving);
                } else {
                    System.out.println(encStrategy.getChangedMessage(initialData));
                }
                break;
            case "dec":
                Strategy decStrategy = new Strategy(new Decryption(), key, algorithm);
                if (fileSaving) {
                    savingToFile(String.valueOf(decStrategy.getChangedMessage(initialData)), fileForSaving);
                } else {
                    System.out.println(decStrategy.getChangedMessage(initialData));
                }
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    private static void savingToFile(String message, String fileForSaving) {
        File file = new File(fileForSaving);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(message);
        } catch (IOException e) {
            System.out.printf("Error with file: %s%n", fileForSaving);
        }
    }
}
