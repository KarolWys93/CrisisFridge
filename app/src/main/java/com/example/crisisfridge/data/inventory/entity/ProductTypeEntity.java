/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.example.crisisfridge.data.inventory.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.inventory.interfaces.ProductType;

import java.util.Objects;


@Entity(tableName = "product_type")
public class ProductTypeEntity implements ProductType {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "expiration")
    private int expiration;
    @ColumnInfo(name = "description")
    private int description;

    public ProductTypeEntity(int id, String name, int expiration, int description) {
        this.id = id;
        this.name = name;
        this.expiration = expiration;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getExpiration() {
        return expiration;
    }

    @Override
    public int getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTypeEntity that = (ProductTypeEntity) o;
        return id == that.id &&
                name == that.name &&
                expiration == that.expiration &&
                description == that.description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expiration, description);
    }
}
