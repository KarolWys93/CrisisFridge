package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.LocalDB;
import com.example.crisisfridge.data.database.dao.ProductTypeDao;
import com.example.crisisfridge.data.model.dataModel.ProductType;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class ProductTypeRepository implements IProductTypeRepository {

    private ProductTypeDao productTypeDao;

    ProductTypeRepository(LocalDB database) {
        this.productTypeDao = database.getProductTypeDao();
    }

    @Override
    public LiveData<List<ProductType>> getProductTypeListByName(String name, int limit) {
        return Transformations.map(
                productTypeDao.getProductTypeListByName(name.toLowerCase(), limit),
                list -> new ArrayList<>(list)
        );
    }

    @Override
    public LiveData<ProductType> getProductTypeById(int productTypeId) {
        return Transformations.map(productTypeDao.getProductTypeById(productTypeId), input -> (ProductType) input);
    }
}
