package com.example.crisisfridge.data.model.dataModel;

import java.time.LocalDate;

public interface FridgeItem {
    int getId();

    int getProductId();

    String getName();

    float getQuantity();

    LocalDate getExpirationDate();

    void setQuantity(float quantity);

    void setExpirationDate(LocalDate expirationDate);

}
