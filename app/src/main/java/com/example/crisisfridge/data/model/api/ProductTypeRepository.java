package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.database.entity.ProductTypeEntity;
import com.example.crisisfridge.data.model.DatabaseMock;
import com.example.crisisfridge.data.model.dataModel.ProductType;
import com.example.crisisfridge.data.model.dataModel.ProductTypeImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTypeRepository implements IProductTypeRepository {

    private Map<Integer, ProductTypeEntity> productTypeEntityMap;

    ProductTypeRepository(DatabaseMock database) {
        productTypeEntityMap = new HashMap<>();
        for (ProductTypeEntity entity : database.getProductType()) {
            productTypeEntityMap.put(entity.getId(), entity);
        }
    }

    @Override
    public List<ProductType> getAllProductType() {
        List<ProductType> resultList = new ArrayList<>();
        for (ProductTypeEntity entity : productTypeEntityMap.values()) {
            resultList.add(new ProductTypeImpl(entity));
        }
        return resultList;
    }

    @Override
    public List<ProductType> getProductTypeListByName(String name, int limit) {
        List<ProductType> resultList = new ArrayList<>();
        int found = 0;
        for (ProductTypeEntity entity : productTypeEntityMap.values()) {
            if (entity.getName().contains(name)) {
                resultList.add(new ProductTypeImpl(entity));
                found++;
            }
            if (found == limit) break;
        }
        return resultList;
    }

    @Override
    public ProductType getProductTypeById(int productTypeId) {
        if (productTypeEntityMap.containsKey(productTypeId)) {
            return new ProductTypeImpl(productTypeEntityMap.get(productTypeId));
        } else {
            return null;
        }
    }
}
