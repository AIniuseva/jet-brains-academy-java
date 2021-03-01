package com.app.service;

import com.app.model.EncryptionDecryptionModel;

public class Decryption extends EncryptionDecryptionModel {

    public Decryption(String message) {
        super(message);
    }

    public StringBuilder keyDecryptor(int key) {
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < this.getMessage().length(); i++) {
            decryptedMessage.append((char) (this.getMessage().charAt(i) - key));
        }
        return decryptedMessage;
    }
}
