package com.example.crisisfridge.data.model.inventory.interfaces;

import java.util.Date;

public interface Pantry {

    public int getId();

    public int getProductId();

    public int getItemId();

    public Date getExpirationDate();

    public Date getOpenDate();

    public float getQuantity();
}
