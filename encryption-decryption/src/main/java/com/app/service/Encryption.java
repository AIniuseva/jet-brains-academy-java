package com.app.service;

import com.app.model.EncryptionDecryptionModel;

public class Encryption extends EncryptionDecryptionModel {

    public Encryption(String message) {
        super(message);
    }

    public StringBuilder keyEncryptor(int key) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < this.getMessage().length(); i++) {
            encryptedMessage.append((char) (this.getMessage().charAt(i) + key));
        }
        return encryptedMessage;
    }
}
