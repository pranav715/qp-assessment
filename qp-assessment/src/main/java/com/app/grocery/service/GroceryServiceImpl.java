package com.app.grocery.service;

import com.app.grocery.dao.GroceryRepo;
import com.app.grocery.model.GroceryItem;
import com.app.grocery.model.Order;
import com.app.grocery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroceryServiceImpl implements GroceryService{

    private GroceryRepo groceryRepo;

    public GroceryServiceImpl(){}

    @Autowired
    public GroceryServiceImpl(GroceryRepo groceryRepo) {
        this.groceryRepo = groceryRepo;
    }

    @Override
    public String addGroceryItem(GroceryItem groceryItem) {
        String message = "Grocery Item added sucessfully";
        try{
            groceryRepo.addGroceryItem(groceryItem);
        }catch (Exception e){
            message = "Error while adding Grocery Item";
        }
        return message;
    }

    @Override
    public List<GroceryItem> getAllGroceryItems() {
        List<GroceryItem> itemList = groceryRepo.getAllGroceryItems();
        return itemList == null ? new ArrayList<>() : itemList;
    }

    @Override
    public String deleteGroceryItem(int id) {
        String message = "Grocery Item deleted sucessfully";
        try{
            groceryRepo.deleteGroceryItem(id);
        }catch (Exception e){
            message = "Error while deleting Grocery Item";
        }
        return message;
    }

    @Override
    public String updateGroceryItem(int id, GroceryItem groceryItem) {
        String message = "Grocery item updated sucessfully";
        try{
            GroceryItem item = groceryRepo.getGroceryItemById(id);
            if(groceryItem.getName() != null)
                item.setName(groceryItem.getName());
            if(groceryItem.getPrice() != null)
                item.setPrice(groceryItem.getPrice());
            groceryRepo.updateGroceryItem(item);
        }catch (Exception e){
            message = "Error while updating grocery item";
        }
        return message;
    }

    @Override
    public String updateGroceryInventory(int id, int inventory) {
        String message = "Grocery item inventory updated sucessfully";
        try{
            GroceryItem item = groceryRepo.getGroceryItemById(id);
            item.setInventory(inventory);
        }catch (Exception e){
            message = "error while updating inventory";
        }
        return message;
    }

    @Override
    public List<GroceryItem> getAvailableGroceryItems() {
        return groceryRepo.getAvailableGroceryItems();
    }

    @Override
    public Order addOrder(int userId, Map<Integer,Integer> itemsAndQuantity) {
        List<Integer> idList = new ArrayList(itemsAndQuantity.keySet());
        List<GroceryItem> groceryItemList = groceryRepo.getGroceryItemsForIds(idList);
        for (GroceryItem item : groceryItemList){
            item.setInventory(itemsAndQuantity.get(item.getId()));
        }
        User user = groceryRepo.getUserBasedOnId(userId);
        Order order = new Order(user, groceryItemList);
        order.setAmount(getOrderAmount(order));
        groceryRepo.addOrder(order);
        return order;
    }

    private double getOrderAmount(Order order){
        double sum = 0.0;
        for(GroceryItem item: order.getGroceryItemList()){
            sum += item.getPrice() * item.getInventory();
        }
        return sum;
    }
}
