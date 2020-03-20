package com.example.crisisfridge.data.model.dataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeImpl implements Recipe {

    private int id;
    private String name;
    private String description;
    private Map<ProductType, Float> ingredientMap;

    public RecipeImpl(String name, String description, Map<ProductType, Float> ingredientMap) {
        this.name = name;
        this.description = description;
        this.ingredientMap = ingredientMap;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<RecipeIngredient> getRecipeIngredient() {
        List<RecipeIngredient> ingredients = new ArrayList<>();
        for (ProductType productType:ingredientMap.keySet()) {
            ingredients.add(new RecipeIngredientImpl(productType, ingredientMap.get(productType)));
        }
        return ingredients;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void addIngredient(ProductType productType, float quantity) {
        ingredientMap.put(productType, quantity);
    }

    @Override
    public void removeIngredient(ProductType productType) {
        ingredientMap.remove(productType);
    }
}
