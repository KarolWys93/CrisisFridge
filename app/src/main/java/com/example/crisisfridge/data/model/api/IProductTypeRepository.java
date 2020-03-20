package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.model.dataModel.ProductType;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IProductTypeRepository {

    LiveData<List<ProductType>> getProductTypeListByName(String name, int limit);

    LiveData<ProductType> getProductTypeById(int productTypeId);

}
