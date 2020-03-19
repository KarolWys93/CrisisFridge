package com.example.crisisfridge.data.inventory.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.inventory.interfaces.ProductType;
import com.example.crisisfridge.data.model.inventory.interfaces.RecipeIngredients;

import java.util.Objects;

@Entity(tableName = "recipe_ingredients")
public class RecipeIngredientsEntity implements RecipeIngredients {

    @PrimaryKey
    private int id;
    @ForeignKey(entity = RecipeEntity.class, parentColumns = "recipe_id", childColumns = "recipe_id")
    private int recipe_id;
    @ForeignKey(entity = ProductType.class, parentColumns = "id", childColumns = "product_type")
    private int product_type;
    @ColumnInfo(name = "quantity")
    private float quantity;

    public RecipeIngredientsEntity(int id, int recipe_id, int product_type, float quantity) {
        this.id = id;
        this.recipe_id = recipe_id;
        this.product_type = product_type;
        this.quantity = quantity;
    }

    @Override
    public int getRecipeId() {
        return recipe_id;
    }

    @Override
    public int getProductType() {
        return product_type;
    }

    @Override
    public float getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientsEntity that = (RecipeIngredientsEntity) o;
        return id == that.id &&
                recipe_id == that.recipe_id &&
                product_type == that.product_type &&
                Float.compare(that.quantity, quantity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipe_id, product_type, quantity);
    }
}
