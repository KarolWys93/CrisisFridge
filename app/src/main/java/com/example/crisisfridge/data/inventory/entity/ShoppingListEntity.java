package com.example.crisisfridge.data.inventory.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.inventory.interfaces.ShoppingList;

import java.util.Objects;

@Entity(tableName = "shopping_list")
public class ShoppingListEntity implements ShoppingList {

    @PrimaryKey
    private int item_id;
    @ForeignKey(entity = ProductTypeEntity.class, parentColumns = "id", childColumns = "product_type_id")
    private int product_type_id;
    private float quantity;
    private boolean bought;

    public ShoppingListEntity(int item_id, int product_type_id, float quantity, boolean bought) {
        this.item_id = item_id;
        this.product_type_id = product_type_id;
        this.quantity = quantity;
        this.bought = bought;
    }

    @Override
    public int getItemId() {
        return item_id;
    }

    @Override
    public int getProductTypeId() {
        return product_type_id;
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
        return item_id == that.item_id &&
                product_type_id == that.product_type_id &&
                Float.compare(that.quantity, quantity) == 0 &&
                bought == that.bought;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, product_type_id, quantity, bought);
    }
}
