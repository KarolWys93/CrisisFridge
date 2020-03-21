package com.example.crisisfridge.data.model.api;

public interface IRepositoryFactory {

    IProductTypeRepository getProductTypeRepository();

    IFridgeItemRepository getFridgeItemRepository();

    IRecipeRepository getRecipeRepository();

}
