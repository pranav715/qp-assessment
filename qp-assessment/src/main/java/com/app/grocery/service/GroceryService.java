package com.app.grocery.service;

import com.app.grocery.model.GroceryItem;
import com.app.grocery.model.Order;

import java.util.List;
import java.util.Map;

public interface GroceryService {

    String addGroceryItem(GroceryItem groceryItem);

    List<GroceryItem> getAllGroceryItems();

    String deleteGroceryItem(int id);

    String updateGroceryItem(int id, GroceryItem groceryItem);

    String updateGroceryInventory(int id, int inventory);

    List<GroceryItem> getAvailableGroceryItems();

    Order addOrder(int useId, Map<Integer,Integer> itemsAndQuantity);
}
