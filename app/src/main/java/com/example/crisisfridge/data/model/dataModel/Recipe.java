package com.example.crisisfridge.data.model.dataModel;

import java.util.List;

public interface Recipe {

    int getId();

    String getName();

    String getDescription();

    List<RecipeIngredient> getRecipeIngredient();

    void setName(String name);

    void setDescription(String description);

    void addIngredient(ProductType productType, float quantity);

    void removeIngredient(ProductType productType);
}
