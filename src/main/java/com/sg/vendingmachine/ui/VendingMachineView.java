package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class VendingMachineView {
    UserIO io;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayWelcomeBanner() {
        io.print("**** Welcome to the Virtual Vending Machine! ****" +
                    "\n\nEnter 'exit' to leave at any time." +
                        "\n\nHere is what we have in stock:\n");
    }

    public void displayItem(Item item) {
        io.print("Item Name: " + item.getName()
                + ",  Price: $" + String.format("%.2f", item.getPrice())
                    + ",  Amount in Stock: " + item.getNumAvailable());
        io.print("-----------------------------------------------------------");
    }

    public double promptAmount() {
        Double amount = io.readDouble("How much money would you like to put in?");
        return amount;

    }

    public void printCurrentAmount(double amount) {
        io.print("Your current amount is: " + String.format("%.2f",amount));
    }

    /*
     * Prompts the user for the name of an item or if they would like to add more money.
     */
    public String promptItemOrMoney() {
        String choice = io.readString("Enter the name of the item you would like, " +
                "or enter 'add money' to add more money.");

        return choice;
    }

    /*
     * Exits the program with a statement.
     */
    public void exit() {
        io.print("Goodbye!");
        System.exit(0);
    }

    /*
     * Prints the exception message.
     *
     * @param message - The error message to display.
     */
    public void printExceptionMessage(String message) {
        io.print(message);
    }

    /*
     * Prompt the user if they would like to do another transaction.
     *
     * @return boolean
     */
    public boolean promptAnotherTransaction() {
        String userInput;
        do {
            userInput = io.readString("Would you like to do another transaction? Y or N?");
        } while (!userInput.equals("Y") && !userInput.equals("N"));

        if (userInput.equals("Y")) {
            return true;
        }
        return false;
    }
}
