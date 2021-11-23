package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.NoItemInInventoryException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.ui.VendingMachineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Component
public class VendingMachineController {
    @Autowired
    VendingMachineService service;
    @Autowired
    VendingMachineView view;

    /**
     * Runs the entire program by calling the service and getting user input through the view,
     *
     * @throws FileNotFoundException
     */
    public void run() throws FileNotFoundException {
        view.displayWelcomeBanner();

        // Store and display items in inventory.
        service.loadInventory();
        displayItems();

        // Prompt initial amount.
        double amount = view.promptAmount();
        service.updateAmount(amount);
        view.printCurrentAmount(service.getAmount());

        boolean keepGoing = false;
        do {
            // Get an item and vend it, or add in more money.
            String choice = view.promptItemOrMoney();
            Item item = checkChoice(choice);
            service.updateItemAndAmount(item);
            view.printCurrentAmount(service.getAmount());

            keepGoing = view.promptAnotherTransaction();
        } while (keepGoing);

        // Transactions are finished.  Update the inventory file.
        service.updateFile();

        view.exit();
    }

    /**
     * Checks if the user wants to add more money, exit, or if the given item exists and has at least 1 available.
     * It throws an exception otherwise.
     *
     * @param choice - The user input
     *
     * @return - The item
     */
    private Item checkChoice(String choice) {
        // Add more money to current total.
        if (choice.toLowerCase().equals("add money")) {
            service.updateAmount(view.promptAmount());
            view.printCurrentAmount(service.getAmount());

            String newChoice = view.promptItemOrMoney();
            return checkChoice(newChoice);

        // User wants to exit.
        } else if (choice.toLowerCase().equals("exit")) {
            view.exit();

        // Check if item exists in inventory, otherwise throw an error.
        } else {
            Item item = null;
            try {
                item = service.vendItem(choice);

            } catch (NoItemInInventoryException | InsufficientFundsException e) {
                view.printExceptionMessage(e.getMessage());
                String newChoice = view.promptItemOrMoney();
                return checkChoice(newChoice);
            }
            return item;
        }
        return null;
    }

    /**
     * Displays all items in the inventory that have at least 1 of itself available.
     */
    private void displayItems() {
        List<Item> items = service.getAllItems();

        for (Item item : items) {
            if (item.getNumAvailable() > 0) {
                view.displayItem(item);
            }
        }
    }

}
