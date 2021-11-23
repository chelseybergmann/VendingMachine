package com.sg.vendingmachine.dto;

public class Item {
    private String name;
    private double price;
    private int numAvailable;

    public Item(String name, double price, int numAvailable) {
        this.name = name;
        this.price = price;
        this.numAvailable = numAvailable;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public void vendItem() {
        numAvailable -= 1;
    }

}
