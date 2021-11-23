package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VendingMachineDaoImpl implements VendingMachineDao {
    Map<String,Item> inventory;

    public VendingMachineDaoImpl() {
        inventory = new HashMap<String,Item>();
    }

    /**
     * Get all the items in the inventory.
     *
     * @return An array list of items.
     */
    @Override
    public List<Item> getAllItems() {
        return new ArrayList<Item>(inventory.values());
    }

    /**
     * Adds a new item to the hashmap.
     *
     * @param item - A vending machine item object.
     */
    @Override
    public void addItem(Item item) {
        inventory.put(item.getName(),item);
    }

    /**
     * Vends an item by retrieving it from the inventory.
     *
     * @param choice - A vending machine item object.
     * @return Item - The item being vended, or null if it doesn't exist.
     */
    @Override
    public Item getItem(String choice) throws NoItemInInventoryException {
        Item item = inventory.get(choice);

        return item;
    }


}
