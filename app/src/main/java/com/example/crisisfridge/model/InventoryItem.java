package com.example.crisisfridge.model;

import java.util.Date;

public class InventoryItem implements InventoryItemI {

    private String name;
    private float quantity;
    private Date expirationDate;

    public InventoryItem(String name, float quantity, Date expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public InventoryItem() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getDate() {
        return expirationDate;
    }

    @Override
    public float getQuantity() {
        return quantity;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDate(Date date) {
        this.expirationDate = date;
    }

    @Override
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


}
