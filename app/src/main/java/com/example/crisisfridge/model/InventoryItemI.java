package com.example.crisisfridge.model;

import java.util.Date;

public interface InventoryItemI {
    public String getName();

    public Date getDate();

    public float getQuantity();

    public void setName(String name);

    public void setDate(Date date);

    public void setQuantity(float quantity);
}
