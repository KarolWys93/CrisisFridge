package com.example.crisisfridge.data.model.api;

import android.util.Log;

import com.example.crisisfridge.data.database.entity.RecipeEntity;
import com.example.crisisfridge.data.database.entity.RecipeIngredientEntity;
import com.example.crisisfridge.data.model.DatabaseMock;
import com.example.crisisfridge.data.model.dataModel.FridgeItem;
import com.example.crisisfridge.data.model.dataModel.ProductType;
import com.example.crisisfridge.data.model.dataModel.Recipe;
import com.example.crisisfridge.data.model.dataModel.RecipeImpl;
import com.example.crisisfridge.data.model.dataModel.RecipeIngredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecipeRepository implements IRecipeRepository {

    static private RecipeRepository instance = null;
    private final String TAG = "RecipeRepository";

    private IProductTypeRepository productTypeRepository;
    private Map<Integer, RecipeEntity> recipeEntityMap;
    private List<RecipeIngredientEntity> recipeIngredientEntities;
    private IFridgeItemRepository fridgeItemRepository;

    static public RecipeRepository getInstance(DatabaseMock databaseMock) {
        if (instance == null) {
            instance = new RecipeRepository(databaseMock);
        }
        return instance;
    }


    private RecipeRepository(DatabaseMock databaseMock) {
        productTypeRepository = ProductTypeRepository.getInstance(databaseMock);
        fridgeItemRepository = FridgeItemRepository.getInstance(databaseMock);
        recipeIngredientEntities = databaseMock.getRecipeIngredients();
        recipeEntityMap = new HashMap<>();

        for (RecipeEntity recipeEntity : databaseMock.getRecipe()) {
            recipeEntityMap.put(recipeEntity.getId(), recipeEntity);
        }
    }


    @Override
    public List<Recipe> getAllRecipeList() {
        List<Recipe> resultList = new ArrayList<>();
        for (RecipeEntity recipeEntity : recipeEntityMap.values()) {
            resultList.add(createRecipe(recipeEntity));
        }
        return resultList;
    }

    @Override
    public List<Recipe> getRecipeListByName(String name, int limit) {
        List<Recipe> resultList = new ArrayList<>();
        int found = 0;
        for (RecipeEntity recipeEntity : recipeEntityMap.values()) {
            if (recipeEntity.getName().contains(name)) {
                resultList.add(createRecipe(recipeEntity));
                found++;
            }
            if (found == limit) break;
        }
        return resultList;
    }

    @Override
    public Recipe getRecipeById(int id) {
        if (recipeEntityMap.containsKey(id)) {
            return createRecipe(recipeEntityMap.get(id));
        } else {
            return null;
        }
    }

    @Override
    public List<Boolean> isPossibleToPrepare(List<Integer> recipeIds) {
        List<Boolean> resultList = new ArrayList<>();
        for (Integer id : recipeIds) {
            boolean enoughTest = true;
            for (Boolean isEnough : isEnoughIngredient(getRecipeById(id).getRecipeIngredient(), 1)) {
                if (!isEnough) {
                    enoughTest = false;
                    break;
                }
            }
            resultList.add(enoughTest);
        }
        return resultList;
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        {
            List<RecipeIngredientEntity> ingredientEntitiesToDelete = new ArrayList<>();
            for (RecipeIngredientEntity entity : recipeIngredientEntities) {
                if (entity.getRecipeId() == recipe.getId()) {
                    ingredientEntitiesToDelete.add(entity);
                }
            }
            recipeIngredientEntities.removeAll(ingredientEntitiesToDelete);
        }
        recipeEntityMap.remove(recipe.getId());

    }

    @Override
    public void useRecipe(Recipe recipe, int portions) {
        for (RecipeIngredient ingredient : recipe.getRecipeIngredient()) {
            fridgeItemRepository.consumeProductFromFridge(
                    ingredient.getProductType(),
                    ingredient.getQuantity() * portions
            );
        }
    }

    @Override
    public List<Boolean> isEnoughIngredient(List<RecipeIngredient> recipeIngredients, int portions) {
        List<Boolean> resultList = new ArrayList<>();
        Map<Integer, Float> quantityMap = new HashMap<>();

        for (FridgeItem fridgeItem : fridgeItemRepository.getFridgeItemList()) {
            quantityMap.put(
                    fridgeItem.getProductId(),
                    fridgeItem.getQuantity() + quantityMap.getOrDefault(fridgeItem.getProductId(), 0f)
            );
        }
        Log.d(TAG, "Quantity map: " + quantityMap);
        for (RecipeIngredient ingredient : recipeIngredients) {
            if (ingredient.getQuantity() * portions > quantityMap.getOrDefault(ingredient.getProductType(), 0f)) {
                resultList.add(false);
            } else {
                resultList.add(true);
            }
        }
        return resultList;
    }

    @Override
    public Recipe addRecipe(String name, String description) {
        RecipeEntity recipe = new RecipeEntity(
                DatabaseMock.maxFromList(recipeEntityMap.keySet()) + 1,
                name,
                description
        );
        recipeEntityMap.put(recipe.getId(), recipe);
        return createRecipe(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        if (recipeEntityMap.containsKey(recipe.getId())){
            RecipeEntity recipeEntity = new RecipeEntity(
                    recipe.getId(),
                    recipe.getName(),
                    recipe.getDescription()
            );
            recipeEntityMap.put(recipe.getId(), recipeEntity);

            List<RecipeIngredientEntity> ingredientToDelete = filterRecipeIngredientEntity(recipe.getId());
            recipeIngredientEntities.removeAll(ingredientToDelete);

            for (RecipeIngredient recipeIngredient:recipe.getRecipeIngredient()) {
                RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity(
                        DatabaseMock.maxFromList(
                                recipeIngredientEntities.stream().map(RecipeIngredientEntity::getId).collect(Collectors.toCollection(ArrayList::new))
                        ) + 1,      //0,  //TODO Zamienić listę na mapę?
                        recipe.getId(),
                        recipeIngredient.getProductType().getId(),
                        recipeIngredient.getQuantity()
                );
                Log.d(TAG, "New Ingredient id: " + recipeIngredientEntity.getId());
                recipeIngredientEntities.add(recipeIngredientEntity);
            }
        }
    }

    @Override
    public void sendMissingToShoppingList(Recipe recipe) {
        Log.e(TAG, "sendMissingToShoppingList not implemented yet!");
    }


    private List<RecipeIngredientEntity> filterRecipeIngredientEntity(int recipeId) {
        List<RecipeIngredientEntity> ingredientForRecipe = new ArrayList<>();
        for (RecipeIngredientEntity entity : recipeIngredientEntities) {
            ingredientForRecipe.add(entity);
        }
        return ingredientForRecipe;
    }

    private Recipe createRecipe(RecipeEntity recipeEntity) {
        Map<ProductType, Float> ingredientMap = new HashMap<>();
        for (RecipeIngredientEntity ingredientEntity : filterRecipeIngredientEntity(recipeEntity.getId())) {
            ingredientMap.put(
                    productTypeRepository.getProductTypeById(ingredientEntity.getProductTypeId()),
                    ingredientEntity.getQuantity()
            );
        }
        return new RecipeImpl(recipeEntity.getId(), recipeEntity.getName(), recipeEntity.getDescription(), ingredientMap);
    }

}
