package com.example.crisisfridge.data.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.dataModel.FridgeItem;

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
    private long expirationDate;

    public FridgeItemEntity(int id, int productId, float quantity, long expirationDate) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
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
    public long getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FridgeItemEntity that = (FridgeItemEntity) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (Float.compare(that.quantity, quantity) != 0) return false;
        return expirationDate == that.expirationDate;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + (int) (expirationDate ^ (expirationDate >>> 32));
        return result;
    }
}
