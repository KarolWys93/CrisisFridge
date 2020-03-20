package com.example.crisisfridge.data.model.dataModel;

import com.example.crisisfridge.data.database.entity.FridgeItemEntity;

import java.time.LocalDate;

public class FridgeItemImpl implements FridgeItem {

    private int id;
    private int productId;
    private String name;
    private float quantity;
    private LocalDate expirationDate;

    public FridgeItemImpl(FridgeItemEntity fridgeItemEntity, String name) {
        this.name = name;
        this.id = fridgeItemEntity.getId();
        this.productId = fridgeItemEntity.getProductId();
        this.quantity = fridgeItemEntity.getQuantity();
        this.expirationDate = LocalDate.ofEpochDay(fridgeItemEntity.getExpirationDate());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getQuantity() {
        return quantity;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
