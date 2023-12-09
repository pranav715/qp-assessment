package com.app.grocery.dao;

import com.app.grocery.model.GroceryItem;
import com.app.grocery.model.Order;
import com.app.grocery.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface GroceryRepo {

    GroceryItem getGroceryItemById(int id);

    List<GroceryItem> getGroceryItemsForIds(List<Integer> ids);

    void addGroceryItem(GroceryItem groceryItem);

    List<GroceryItem> getAllGroceryItems();

    void deleteGroceryItem(int id);

    GroceryItem updateGroceryItem(GroceryItem groceryItem);

    GroceryItem updateGroceryInventory(GroceryItem groceryItem);

    List<GroceryItem> getAvailableGroceryItems();

    void addOrder(Order order);

    User getUserBasedOnId(int userId);
}
