/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.example.crisisfridge.data.database.dao;


import com.example.crisisfridge.data.database.entity.RecipeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllRecipe(List<RecipeEntity> recipeEntityList);

}
