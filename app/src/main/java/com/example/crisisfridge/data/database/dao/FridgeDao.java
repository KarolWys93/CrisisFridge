/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.example.crisisfridge.data.database.dao;


import com.example.crisisfridge.data.database.entity.FridgeItemEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface FridgeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFridgeItem(List<FridgeItemEntity> fridgeItemEntityList);

    @Query("SELECT * FROM fridge")
    LiveData<List<FridgeItemEntity>> getAllFridgeItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNewItemToFridge(FridgeItemEntity fridgeItemEntity);

    @Update
    void updateItemFromFridge(FridgeItemEntity fridgeItemEntity);

    @Delete
    void deleteItemFromFridge(FridgeItemEntity fridgeItemEntity);

}

