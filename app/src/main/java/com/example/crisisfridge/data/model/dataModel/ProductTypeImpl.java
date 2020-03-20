package com.example.crisisfridge.data.model.dataModel;

import com.example.crisisfridge.data.database.entity.ProductTypeEntity;

public class ProductTypeImpl implements ProductType {


    private int id;
    private String name;

    public ProductTypeImpl(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductTypeImpl(ProductTypeEntity productTypeEntity){
        this.id = productTypeEntity.getId();
        this.name = productTypeEntity.getName();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
