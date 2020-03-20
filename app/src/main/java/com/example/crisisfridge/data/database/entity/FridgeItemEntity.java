package com.example.crisisfridge.data.database.entity;

public class FridgeItemEntity {

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

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public float getQuantity() {
        return quantity;
    }

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
