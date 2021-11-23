package com.sg.vendingmachine.ui;

public interface UserIO {
    void print(String msg);

    double readDouble(String prompt);

    float readFloat(String prompt);

    int readInt(String prompt);

    long readLong(String prompt);

    String readString(String prompt);
}