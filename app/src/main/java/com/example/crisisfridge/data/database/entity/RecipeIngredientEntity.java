package com.example.crisisfridge.data.database.entity;


public class RecipeIngredientEntity {


    private int id;
    private int recipeId;
    private int productTypeId;
    private float quantity;

    public RecipeIngredientEntity(int id, int recipeId, int productTypeId, float quantity) {
        this.id = id;
        this.recipeId = recipeId;
        this.productTypeId = productTypeId;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }


    public int getRecipeId() {
        return recipeId;
    }


    public int getProductTypeId() {
        return productTypeId;
    }


    public float getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeIngredientEntity that = (RecipeIngredientEntity) o;

        if (id != that.id) return false;
        if (recipeId != that.recipeId) return false;
        if (productTypeId != that.productTypeId) return false;
        return Float.compare(that.quantity, quantity) == 0;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + recipeId;
        result = 31 * result + productTypeId;
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        return result;
    }
}
