package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.model.DatabaseMock;

public class RepositoryFactory implements IRepositoryFactory {

    private DatabaseMock databaseMock;

    public RepositoryFactory(DatabaseMock databaseMock) {
        this.databaseMock = databaseMock;
    }

    @Override
    public IProductTypeRepository getProductTypeRepository() {
        return ProductTypeRepository.getInstance(databaseMock);
    }

    @Override
    public IFridgeItemRepository getFridgeItemRepository() {
        return FridgeItemRepository.getInstance(databaseMock);
    }

    @Override
    public IRecipeRepository getRecipeRepository() {
        return RecipeRepository.getInstance(databaseMock);
    }
}
