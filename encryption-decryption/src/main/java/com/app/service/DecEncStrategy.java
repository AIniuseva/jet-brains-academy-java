package com.app.service;

public interface DecEncStrategy {
    StringBuilder messageUnicodeChanging(String message, int key);

    StringBuilder messageShiftChanging(String message, int key);
}
