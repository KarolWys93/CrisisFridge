package com.example.crisisfridge.data.model.api;

import com.example.crisisfridge.data.model.dataModel.Recipe;
import com.example.crisisfridge.data.model.dataModel.RecipeIngredient;

import java.util.List;

public interface IRecipeRepository {

    List<Recipe> getAllRecipeList();

    List<Recipe> getRecipeListByName(String name, int limit);

    Recipe getRecipeById(int id);

    List<Boolean> isPossibleToPrepare(List<Integer> recipeIds);

    void removeRecipe(Recipe recipe);

    void useRecipe(Recipe recipe, int portions);

    List<Boolean> isMissingIngredient(RecipeIngredient recipeIngredient);

    void updateRecipe(Recipe recipe);

    void sendMissingToShoppingList(Recipe recipe);

}
