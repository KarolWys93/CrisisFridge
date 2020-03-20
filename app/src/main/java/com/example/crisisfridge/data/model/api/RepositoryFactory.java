package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.model.DatabaseMock;

public class RepositoryFactory implements IRepositoryFactory {

    private DatabaseMock databaseMock;

    public RepositoryFactory(DatabaseMock databaseMock) {
        this.databaseMock = databaseMock;
    }

    @Override
    public IProductTypeRepository getProductTypeRepository() {
        return new ProductTypeRepository(databaseMock);
    }

    @Override
    public IFridgeItemRepository getFridgeItemRepository() {
        return new FridgeItemRepository(databaseMock);
    }
}
