package com.example.crisisfridge.data.model.inventory.interfaces;

public interface ShoppingList {
    int getId();

    int getProductTypeId();

    float getQuantity();

    boolean isBought();
}
