package com.example.crisisfridge.data.inventory.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.inventory.interfaces.FridgeItem;

@Entity(tableName = "fridge",
        foreignKeys = @ForeignKey(
                entity = ProductTypeEntity.class,
                parentColumns = "id",
                childColumns = "productId",
                onDelete = ForeignKey.CASCADE),
        indices = @Index("productId")
)
public class FridgeItemEntity implements FridgeItem {

    @PrimaryKey
    private int id;
    private int productId;
    private float quantity;

    public FridgeItemEntity(int id, int productId, float quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public float getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FridgeItemEntity that = (FridgeItemEntity) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        return Float.compare(that.quantity, quantity) == 0;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        return result;
    }
}
