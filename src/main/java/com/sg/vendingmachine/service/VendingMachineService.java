package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.NoItemInInventoryException;
import com.sg.vendingmachine.dto.Item;

import java.io.FileNotFoundException;
import java.util.List;

public interface VendingMachineService {
    void loadInventory();

    List<Item> getAllItems();

    Item vendItem(String choice) throws NoItemInInventoryException, InsufficientFundsException;

    void updateItemAndAmount(Item item);

    double getAmount();

    void updateAmount(double toAdd);

    void updateFile() throws FileNotFoundException;

    void subtractAmount(double toSubtract);

}
