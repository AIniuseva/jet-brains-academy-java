package com.app;

import com.app.service.Decryption;
import com.app.service.Encryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EncryptionDecryption {
    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String data = null;
        String dataFile = null;
        String savingFile = null;

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
            }
        }

        if (fileReading) {
            File file = new File(dataFile);
            try (Scanner scanner = new Scanner(file)) {
                data = scanner.nextLine();
            } catch (FileNotFoundException e) {
                System.out.printf("File not found: %s%n", file.getName());
            }
        }

        if ("enc".equals(mode)) {
            Encryption encryption = new Encryption(data);
            if (fileSaving) {
                try (FileWriter fileWriter = new FileWriter(savingFile)) {
                    fileWriter.write(String.valueOf(encryption.keyEncryptor(key)));
                } catch (IOException e) {
                    System.out.printf("Error with file: %s%n", savingFile);
                }
            } else {
                System.out.println(encryption.keyEncryptor(key));
            }
        } else {
            Decryption decryption = new Decryption(data);
            if (fileSaving) {
                try (FileWriter fileWriter = new FileWriter(savingFile)) {
                    fileWriter.write(String.valueOf(decryption.keyDecryptor(key)));
                } catch (IOException e) {
                    System.out.printf("Error with file: %s%n", savingFile);
                }
            } else {
                System.out.println(decryption.keyDecryptor(key));
            }
        }
    }
}
