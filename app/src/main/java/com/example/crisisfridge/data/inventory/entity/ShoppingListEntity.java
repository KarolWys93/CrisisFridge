package com.example.crisisfridge.data.inventory.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.inventory.interfaces.ShoppingList;

import java.util.Objects;

@Entity(tableName = "shopping_list",
        foreignKeys = @ForeignKey(
                entity = ProductTypeEntity.class,
                parentColumns = "id",
                childColumns = "productTypeId",
                onDelete = ForeignKey.CASCADE),
        indices = @Index("productTypeId")
)
public class ShoppingListEntity implements ShoppingList {

    @PrimaryKey
    private int id;
    private int productTypeId;
    private float quantity;
    private boolean bought;

    public ShoppingListEntity(int id, int productTypeId, float quantity, boolean bought) {
        this.id = id;
        this.productTypeId = productTypeId;
        this.quantity = quantity;
        this.bought = bought;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getProductTypeId() {
        return productTypeId;
    }

    @Override
    public float getQuantity() {
        return quantity;
    }

    @Override
    public boolean isBought() {
        return bought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingListEntity that = (ShoppingListEntity) o;

        if (id != that.id) return false;
        if (productTypeId != that.productTypeId) return false;
        if (Float.compare(that.quantity, quantity) != 0) return false;
        return bought == that.bought;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productTypeId;
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + (bought ? 1 : 0);
        return result;
    }
}
