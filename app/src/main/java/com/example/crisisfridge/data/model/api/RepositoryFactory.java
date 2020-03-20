package com.example.crisisfridge.data.model.api;

import android.content.Context;

import com.example.crisisfridge.data.LocalDB;

public class RepositoryFactory implements IRepositoryFactory {

    private Context context;

    public RepositoryFactory(Context context) {
        this.context = context;
    }

    @Override
    public IProductTypeRepository getProductTypeRepository() {
        return new ProductTypeRepository(LocalDB.getDatabase(context));
    }

    @Override
    public IFridgeItemRepository getFridgeItemRepository() {
        return new FridgeItemRepository(LocalDB.getDatabase(context));
    }
}
