/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.example.crisisfridge.data.database.dao;

import com.example.crisisfridge.data.database.entity.RecipeIngredientEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface RecipeIngredientsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllRecipeIngredients(List<RecipeIngredientEntity> recipeIngredientEntityList);
}
