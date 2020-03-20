/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.example.crisisfridge.data.database.dao;


import com.example.crisisfridge.data.database.entity.ProductTypeEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface ProductTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllProductType(List<ProductTypeEntity> productTypeEntityList);

    @Query("SELECT * FROM product_type WHERE id = :id")
    LiveData<ProductTypeEntity> getProductTypeById(int id);

    @Query("SELECT * FROM product_type WHERE id IN (:ids)")
    LiveData<List<ProductTypeEntity>> getProductTypeByIdList(List<Integer> ids);

    @Query("SELECT * FROM product_type WHERE name LIKE :name LIMIT :limit")
    LiveData<List<ProductTypeEntity>> getProductTypeListByName(String name, int limit);
}
