package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {
    List<Item> getAllItems();

    void addItem(Item item);

    Item getItem(String choice) throws NoItemInInventoryException;

}
