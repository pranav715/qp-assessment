package com.app.grocery.controller;

import com.app.grocery.model.GroceryItem;
import com.app.grocery.model.Order;
import com.app.grocery.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GroceryController {

    private GroceryService groceryService;

    public GroceryController() {}

    @Autowired
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    // ADMIN Role
    @PostMapping("/add/grocery-item")
    public String addGroceryItem(@RequestBody GroceryItem groceryItem){
        return groceryService.addGroceryItem(groceryItem);
    }

    @GetMapping("/get-all")
    public List<GroceryItem> getAllGroceryItems(){
        return groceryService.getAllGroceryItems();
    }

    @GetMapping("/delete/{id}")
    public String deleteGroceryItem(@PathVariable int id){
        return groceryService.deleteGroceryItem(id);
    }

    @PostMapping("/update/grocery-item/{id}")
    public String updateGroceyItem(@PathVariable int id, @RequestBody GroceryItem groceryItem){
        return groceryService.updateGroceryItem(id, groceryItem);
    }

    @PostMapping("/update/{id}/inventory")
    public String updateGroceryInventory(@PathVariable int id, @RequestBody int invenroty){
        return groceryService.updateGroceryInventory(id, invenroty);
    }

    //USER role
    @GetMapping("/get")
    public List<GroceryItem> viewGroceryItems(){
        return groceryService.getAvailableGroceryItems();
    }

    @PostMapping("/add-order/{userId}")
    public Order createOrder(@PathVariable int userId ,@RequestBody Map<Integer,Integer> itemsAndQuantity){
        return groceryService.addOrder(userId, itemsAndQuantity);
    }
}
