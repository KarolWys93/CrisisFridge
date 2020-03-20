package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.model.dataModel.FridgeItem;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IFridgeItemRepository {

    LiveData<List<FridgeItem>> getFridgeItemList();

    void addNewItemToFridge(int productTypeID, int quantity, int expirationDate);

    void removeItemFromFridge(int itemID);

    void editItemFromFridge(int itemID, int quantity, int expirationDate);

    void consumeProductFromFridge(int productTypeID, int consumedQuantity);

}
