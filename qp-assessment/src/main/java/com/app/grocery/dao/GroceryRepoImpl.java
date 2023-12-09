package com.app.grocery.dao;

import com.app.grocery.model.GroceryItem;
import com.app.grocery.model.Order;
import com.app.grocery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GroceryRepoImpl implements GroceryRepo{

    private EntityManager entityManager;

    public GroceryRepoImpl(){}

    @Autowired
    public GroceryRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public GroceryItem getGroceryItemById(int id) {
        return entityManager.find(GroceryItem.class, id);
    }

    @Override
    public List<GroceryItem> getGroceryItemsForIds(List<Integer> ids) {
        TypedQuery<GroceryItem> query = entityManager.createQuery("FROM GroceryItem gi where gi.id IN (:ids)", GroceryItem.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addGroceryItem(GroceryItem groceryItem) {
        entityManager.persist(groceryItem);
    }

    @Override
    public List<GroceryItem> getAllGroceryItems() {
        TypedQuery<GroceryItem> query = entityManager.createQuery("FROM GroceryItem ", GroceryItem.class);
        return query.getResultList();
    }

    @Override
    public void deleteGroceryItem(int id) {
        GroceryItem item = entityManager.find(GroceryItem.class, id);
        entityManager.remove(item);
    }

    @Override
    @Transactional
    public GroceryItem updateGroceryItem(GroceryItem groceryItem) {
        return entityManager.merge(groceryItem);
    }

    @Override
    @Transactional
    public GroceryItem updateGroceryInventory(GroceryItem groceryItem) {
        return entityManager.merge(groceryItem);
    }

    @Override
    public List<GroceryItem> getAvailableGroceryItems() {
        TypedQuery<GroceryItem> query = entityManager.createQuery("FROM GroceryItem gi WHERE gi.inventory > 0", GroceryItem.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public User getUserBasedOnId(int userId) {
        return entityManager.find(User.class, userId);
    }
}
