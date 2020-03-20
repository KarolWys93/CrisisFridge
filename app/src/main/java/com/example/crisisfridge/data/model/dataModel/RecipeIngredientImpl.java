package com.example.crisisfridge.data.model.dataModel;

public class RecipeIngredientImpl implements RecipeIngredient {

    private ProductType productType;
    private float quantity;

    public RecipeIngredientImpl(ProductType productType, float quantity) {
        this.productType = productType;
        this.quantity = quantity;
    }

    @Override
    public ProductType getProductType() {
        return productType;
    }

    @Override
    public float getQuantity() {
        return quantity;
    }
}
