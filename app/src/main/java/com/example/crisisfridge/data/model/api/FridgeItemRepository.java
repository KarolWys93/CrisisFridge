package com.example.crisisfridge.data.model.api;

import android.util.Log;

import com.example.crisisfridge.data.LocalDB;
import com.example.crisisfridge.data.database.dao.FridgeDao;
import com.example.crisisfridge.data.database.dao.ProductTypeDao;
import com.example.crisisfridge.data.database.entity.FridgeItemEntity;
import com.example.crisisfridge.data.database.entity.ProductTypeEntity;
import com.example.crisisfridge.data.model.dataModel.FridgeItem;
import com.example.crisisfridge.data.model.dataModel.FridgeItemImpl;
import com.example.crisisfridge.data.model.dataModel.ProductType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class FridgeItemRepository implements IFridgeItemRepository {

    private final String TAG = "FridgeItemRepository";
    private FridgeDao fridgeDao;
    private ProductTypeDao productTypeDao;

    private LiveData<List<FridgeItemEntity>> fridgeItemEntityLiveData;
    private MediatorLiveData<List<FridgeItem>> mergedFridgeItemsLiveData;
    private MutableLiveData<List<Integer>> productTypeIdsLiveData;

    private MutableLiveData<FridgeItemEntity> addItemTrigger;
    private MediatorLiveData<FridgeItemEntity> newFridgeItem;

    FridgeItemRepository(LocalDB database) {
        this.fridgeDao = database.getFridgeDao();
        this.productTypeDao = database.getProductTypeDao();


        createFridgeItemFlow();
        createAddFridgeItemFlow();
    }

    private void createFridgeItemFlow(){
        fridgeItemEntityLiveData = fridgeDao.getAllFridgeItems();
        productTypeIdsLiveData = new MutableLiveData<>();
        mergedFridgeItemsLiveData = new MediatorLiveData<>();
        mergedFridgeItemsLiveData.addSource(fridgeItemEntityLiveData, fridgeItemEntityList -> {
            List<Integer> productTypeIdList = new ArrayList<>();
            for (FridgeItemEntity entity : fridgeItemEntityList) {
                productTypeIdList.add(entity.getProductId());
            }
            productTypeIdsLiveData.setValue(productTypeIdList);
        });
        mergedFridgeItemsLiveData.addSource(Transformations.switchMap(productTypeIdsLiveData, input -> productTypeDao.getProductTypeByIdList(input)), productTypeEntityList -> {
            if (fridgeItemEntityLiveData.getValue() == null) return;
            List<FridgeItem> fridgeItemList = new ArrayList<>();
            Map<Integer, String> productNames = new HashMap<>();
            for (ProductTypeEntity productTypeEntity:productTypeEntityList) {
                productNames.put(productTypeEntity.getId(), productTypeEntity.getName());
            }
            for (FridgeItemEntity fridgeItemEntity:fridgeItemEntityLiveData.getValue()) {
                FridgeItem fridgeItem = new FridgeItemImpl(
                        fridgeItemEntity,
                        productNames.get(fridgeItemEntity.getProductId())
                );
                fridgeItemList.add(fridgeItem);
            }
            mergedFridgeItemsLiveData.setValue(fridgeItemList);
        });
    }

    private void createAddFridgeItemFlow(){
        newFridgeItem = new MediatorLiveData<>();
        addItemTrigger = new MutableLiveData<>();
        newFridgeItem.addSource(addItemTrigger, fridgeDao::addNewItemToFridge);
    }

    @Override
    public LiveData<List<FridgeItem>> getFridgeItemList() {
        return mergedFridgeItemsLiveData;
    }

    @Override
    public void addNewItemToFridge(ProductType productType, int quantity, LocalDate expirationDate) {
        FridgeItemEntity fridgeItemEntity = new FridgeItemEntity(
                0,
                productType.getId(),
                quantity,
                expirationDate.toEpochDay());
        Log.d(TAG, "Add item to fridge: " + fridgeItemEntity);
        addItemTrigger.setValue(fridgeItemEntity);
    }

    @Override
    public void removeItemFromFridge(FridgeItem fridgeItem) {
        Log.d(TAG, "Remove item from fridge " + fridgeItem);
        fridgeDao.deleteItemFromFridge(createFridgeItemEntity(fridgeItem));
    }

    @Override
    public void editItemFromFridge(FridgeItem fridgeItem) {
        Log.d(TAG, "Update item in fridge " + fridgeItem);
        fridgeDao.updateItemFromFridge(createFridgeItemEntity(fridgeItem));
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
