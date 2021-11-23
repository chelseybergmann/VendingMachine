package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.NoItemInInventoryException;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

@Component
public class VendingMachineServiceImpl implements VendingMachineService {
    VendingMachineDao dao;
    Scanner scanner;
    File file = new File("inventory.txt");
    final String DELIMITER = "::";
    double amount;

    @Autowired
    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
        amount = 0;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads every item from inventory file into a hashmap.
     */
    @Override
    public void loadInventory() {
        while (scanner.hasNextLine()) {
            Item item = unmarshall(scanner.nextLine());
            dao.addItem(item);

        }
    }

    /*
     * Gets all of the inventory items as an array list.
     *
     * @return An array list of the items.
     */
    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    /**
     * Gets the item from inventory given its name, and returns it.
     *
     * @param choice - The name of item to vend.
     * @return Item - The vending machine item.
     */
    @Override
    public Item vendItem(String choice) throws NoItemInInventoryException, InsufficientFundsException {
        Item item = dao.getItem(choice);

        if (item == null) {
            throw new NoItemInInventoryException("This item does not exist in the inventory.");
        } else if (item.getNumAvailable() == 0) {
            throw new NoItemInInventoryException("Sorry, there is no more of this item available.");
        } else if (getAmount() < item.getPrice()) {
            throw new InsufficientFundsException("Sorry, you need to add more money to purchase this item.");
        } else {
            return item;
        }
    }

    /**
     * Change the number available by 1 and subtract its price from the current
     * amount the user has in.
     *
     * @param Item - The item to change/vend.
     */
    @Override
    public void updateItemAndAmount(Item item) {
        item.vendItem();
        subtractAmount(item.getPrice());
    }

    /**
     * Creates a Vending Machine Item object from the given string.
     *
     * @param line - A string separated by the delimiter '::'
     * @return Item - the data transfer object (dto)
     */
    private Item unmarshall(String line) {
        String[] itemInfo = line.split(DELIMITER);
        return new Item(itemInfo[0], Double.valueOf(itemInfo[1]), Integer.valueOf(itemInfo[2]));
    }

    /*
     * Get the amount of money the user currently has in the machine.
     */
    @Override
    public double getAmount() {
        return amount;
    }

    /*
     * Update the current amount.
     *
     * @param toAdd - the amount of money as a double to add to the current amount.
     */
    @Override
    public void updateAmount(double toAdd) {
        amount += toAdd;
    }

    /*
     * Updates the file through marshalling/serialization.
     */
    @Override
    public void updateFile() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(file);

        List<Item> items = dao.getAllItems();
        for (Item item : items) {
            String itemInfo = marshall(item);
            out.println(itemInfo);
        }
        out.flush();
    }

    /*
     * Takes in a vending machine item and converts it to a string
     * separated by '::' in order to write it back to the file.
     *
     * @param item - the item to serialize.
     * @return String - its representation in string form,
     */
    private String marshall(Item item) {
        return item.getName() + DELIMITER + item.getPrice() + DELIMITER + item.getNumAvailable();
    }
    /*
     * Update the current amount by subtracting the given amount.
     *
     * @param toSubtract - the amount of money as a double to subtract from the current amount.
     */
    @Override
    public void subtractAmount(double toSubtract) {
       this.amount -= toSubtract;
    }
}
