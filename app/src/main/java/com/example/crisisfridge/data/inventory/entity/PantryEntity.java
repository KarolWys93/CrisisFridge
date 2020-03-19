package com.example.crisisfridge.data.inventory.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.inventory.interfaces.Pantry;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "pantry")
public class PantryEntity implements Pantry {

    @PrimaryKey
    private int inv_item;
    @ForeignKey(entity = ProductTypeEntity.class, parentColumns = "id", childColumns = "product_id")
    private int product_id;
    @ColumnInfo(name = "name")
    private int id_item;
    @ColumnInfo(name = "expiration_date")
    private Date expiration_date;
    @ColumnInfo(name = "open_date")
    private Date open_date;
    @ColumnInfo(name = "quantity")
    private float quantity;

    @Override
    public int getId() {
        return inv_item;
    }

    @Override
    public int getProductId() {
        return product_id;
    }

    @Override
    public int getItemId() {
        return id_item;
    }

    @Override
    public Date getExpirationDate() {
        return expiration_date;
    }

    @Override
    public Date getOpenDate() {
        return open_date;
    }

    @Override
    public float getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PantryEntity that = (PantryEntity) o;
        return inv_item == that.inv_item &&
                product_id == that.product_id &&
                id_item == that.id_item &&
                Float.compare(that.quantity, quantity) == 0 &&
                Objects.equals(expiration_date, that.expiration_date) &&
                Objects.equals(open_date, that.open_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inv_item, product_id, id_item, expiration_date, open_date, quantity);
    }

}
