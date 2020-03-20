package com.example.crisisfridge.data.model.api;

import android.util.Log;


import com.example.crisisfridge.data.database.entity.FridgeItemEntity;
import com.example.crisisfridge.data.model.DatabaseMock;
import com.example.crisisfridge.data.model.dataModel.FridgeItem;
import com.example.crisisfridge.data.model.dataModel.FridgeItemImpl;
import com.example.crisisfridge.data.model.dataModel.ProductType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class FridgeItemRepository implements IFridgeItemRepository {

    private final String TAG = "FridgeItemRepository";


    private Map<Integer, FridgeItemEntity> fridgeItemEntityMap;
    private IProductTypeRepository productTypeRepository;


    FridgeItemRepository(DatabaseMock databaseMock) {
        fridgeItemEntityMap = new HashMap<>();
        for (FridgeItemEntity entity : databaseMock.getFridge()) {
            fridgeItemEntityMap.put(entity.getId(), entity);
        }
        productTypeRepository = new RepositoryFactory(databaseMock).getProductTypeRepository();
    }


    @Override
    public List<FridgeItem> getFridgeItemList() {

        List<FridgeItem> resultList = new ArrayList<>();

        for (FridgeItemEntity entity : fridgeItemEntityMap.values()) {
            ProductType productType = productTypeRepository.getProductTypeById(entity.getProductId());
            if (productType == null) continue;
            resultList.add(new FridgeItemImpl(
                    entity,
                    productType.getName())
            );
        }
        return resultList;
    }

    @Override
    public void addNewItemToFridge(ProductType productType, float quantity, LocalDate expirationDate) {
        int newID = DatabaseMock.maxFromList(fridgeItemEntityMap.keySet()) + 1;
        FridgeItemEntity fridgeItemEntity = new FridgeItemEntity(
                newID,
                productType.getId(),
                quantity,
                expirationDate.toEpochDay()
        );
        fridgeItemEntityMap.put(newID, fridgeItemEntity);
    }

    @Override
    public void removeItemFromFridge(FridgeItem fridgeItem) {
        Log.d(TAG, "Remove item from fridge " + fridgeItem);
        fridgeItemEntityMap.remove(fridgeItem.getId());
    }

    @Override
    public void editItemFromFridge(FridgeItem fridgeItem) {
        Log.d(TAG, "Update item in fridge " + fridgeItem);
        FridgeItemEntity fridgeItemEntity = createFridgeItemEntity(fridgeItem);
        if (fridgeItemEntityMap.containsKey(fridgeItem.getId())) {
            fridgeItemEntityMap.put(fridgeItem.getId(), fridgeItemEntity);
        }
    }

    @Override
    public void consumeProductFromFridge(ProductType productType, float consumedQuantity) {
        Log.d(TAG, "Consume products: " + productType + " quantity: " + consumedQuantity);
        Log.e(TAG, "Consume products. NOT IMPLEMENTED!");
    }

    private FridgeItemEntity createFridgeItemEntity(FridgeItem fridgeItem) {
        int item_id = fridgeItem.getId();
        int product_id = fridgeItem.getProductId();
        float quantity = fridgeItem.getQuantity();
        long expirationDate = fridgeItem.getExpirationDate().toEpochDay();
        return new FridgeItemEntity(item_id, product_id, quantity, expirationDate);
    }
}
