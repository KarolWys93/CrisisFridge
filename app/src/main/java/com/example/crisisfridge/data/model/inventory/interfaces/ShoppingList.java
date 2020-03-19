package com.example.crisisfridge.data.model.inventory.interfaces;

public interface ShoppingList {
    public int getItemId();
    public int getProductTypeId();
    public float getQuantity();
    public boolean isBought();
}
