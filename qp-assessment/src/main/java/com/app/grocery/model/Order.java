package com.app.grocery.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "order_items", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<GroceryItem> groceryItemList;

    @Column(name = "amount")
    private double amount;

    public Order() {}

    public Order(User user, List<GroceryItem> groceryItemList) {
        this.user = user;
        this.groceryItemList = groceryItemList;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<GroceryItem> getGroceryItemList() {
        return groceryItemList;
    }

    public void setGroceryItemList(List<GroceryItem> groceryItemList) {
        this.groceryItemList = groceryItemList;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", groceryItemList=" + groceryItemList +
                ", amount=" + amount +
                '}';
    }

}
