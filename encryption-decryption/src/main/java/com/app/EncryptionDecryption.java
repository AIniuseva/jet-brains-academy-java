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
    private static String data = null;
    private static String savingFile = null;

    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String dataFile = null;
        String alg = null;
        boolean fileSaving = false;
        boolean fileReading = false;

        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                mode = args[i + 1];
            } else if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            } else if ("-data".equals(args[i])) {
                data = args[i + 1];
            } else if ("-in".equals(args[i])) {
                dataFile = args[i + 1];
                fileReading = true;
            } else if ("-out".equals(args[i])) {
                savingFile = args[i + 1];
                fileSaving = true;
            } else if ("-alg".equals(args[i])) {
                alg = args[i + 1];
            }
        }

        if (fileReading) {
            File file = new File(dataFile);
            try (Scanner scanner = new Scanner(file)) {
                data = String.valueOf(scanner.nextLine());
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + dataFile);
            }
        }

        switch (mode) {
            case "enc":
                Strategy encStrategy = new Strategy(new Encryption(), key, alg);
                if (fileSaving) {
                    savingToFile(String.valueOf(encStrategy.getChangedMessage(data)));
                } else {
                    System.out.println(encStrategy.getChangedMessage(data));
                }
                break;
            case "dec":
                Strategy decStrategy = new Strategy(new Decryption(), key, alg);
                if (fileSaving) {
                    savingToFile(String.valueOf(decStrategy.getChangedMessage(data)));
                } else {
                    System.out.println(decStrategy.getChangedMessage(data));
                }
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    private static void savingToFile(String message) {
        File file = new File(savingFile);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(message);
        } catch (IOException e) {
            System.out.printf("Error with file: %s%n", savingFile);
        }
    }
}
