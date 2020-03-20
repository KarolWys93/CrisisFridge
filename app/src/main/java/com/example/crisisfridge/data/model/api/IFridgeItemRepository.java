package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.model.dataModel.FridgeItem;
import com.example.crisisfridge.data.model.dataModel.ProductType;

import java.time.LocalDate;
import java.util.List;

import androidx.lifecycle.LiveData;

public interface IFridgeItemRepository {

    LiveData<List<FridgeItem>> getFridgeItemList();

    void addNewItemToFridge(ProductType productType, int quantity, LocalDate expirationDate);

    void removeItemFromFridge(FridgeItem fridgeItem);

    void editItemFromFridge(FridgeItem fridgeItem);

    void consumeProductFromFridge(ProductType productType, float consumedQuantity);

}
