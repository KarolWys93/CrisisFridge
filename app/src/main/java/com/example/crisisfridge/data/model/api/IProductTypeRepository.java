package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.model.dataModel.ProductType;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IProductTypeRepository {

    List<ProductType> getAllProductType();

    List<ProductType> getProductTypeListByName(String name, int limit);

    ProductType getProductTypeById(int productTypeId);

}
