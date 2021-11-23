package com.sg.vendingmachine.service;

public class InsufficientFundsException extends Throwable {

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message,
                                  Throwable cause) {
        super(message, cause);
    }
}
