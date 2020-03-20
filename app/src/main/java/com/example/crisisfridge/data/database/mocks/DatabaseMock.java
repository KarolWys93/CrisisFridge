package com.example.crisisfridge.data.database.mocks;

import com.example.crisisfridge.data.database.entity.FridgeItemEntity;
import com.example.crisisfridge.data.database.entity.ProductTypeEntity;
import com.example.crisisfridge.data.database.entity.RecipeEntity;
import com.example.crisisfridge.data.database.entity.RecipeIngredientEntity;
import com.example.crisisfridge.data.database.entity.ShoppingListEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseMock {

    static DatabaseMock instance = null;

    private List<FridgeItemEntity> fridge;
    private List<ProductTypeEntity> productType;
    private List<RecipeEntity> recipe;
    private List<RecipeIngredientEntity> recipeIngredients;
    private List<ShoppingListEntity> shoppingList;


    static public DatabaseMock getInstance(){
        if (instance == null){
            instance = new DatabaseMock();
        }
        return instance;
    }

    private DatabaseMock() {

        productType = new ArrayList<>(Arrays.asList(
                new ProductTypeEntity(1, "jajka"),
                new ProductTypeEntity(2, "bekon"),
                new ProductTypeEntity(3, "ser"),
                new ProductTypeEntity(4, "pomidory")
        ));

        fridge = new ArrayList<>(Arrays.asList(
                new FridgeItemEntity(1, 1, 5, LocalDate.now().plusDays(5).toEpochDay()),
                new FridgeItemEntity(2, 3, 300, LocalDate.now().plusDays(7).toEpochDay())
        ));

        recipe = new ArrayList<>(Arrays.asList(
                new RecipeEntity(1, "jajecznica", "Jajecznica jaka jest każdy widzi.")
        ));
        recipeIngredients = new ArrayList<>(Arrays.asList(
                new RecipeIngredientEntity(1, 1, 1, 2),
                new RecipeIngredientEntity(2, 1, 2, 1)
        ));
        shoppingList = new ArrayList<>();
    }

    public List<FridgeItemEntity> getFridge() {
        return fridge;
    }

    public List<ProductTypeEntity> getProductType() {
        return productType;
    }

    public List<RecipeEntity> getRecipe() {
        return recipe;
    }

    public List<RecipeIngredientEntity> getRecipeIngredients() {
        return recipeIngredients;
    }

    public List<ShoppingListEntity> getShoppingList() {
        return shoppingList;
    }
}
