package com.sg.vendingmachine.dao;

public class NoItemInInventoryException extends Throwable {

    public NoItemInInventoryException(String message) {
        super(message);
    }

    public NoItemInInventoryException(String message,
                                      Throwable cause) {
        super(message, cause);
    }
}
