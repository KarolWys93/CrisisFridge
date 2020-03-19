/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.example.crisisfridge.data;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.crisisfridge.data.inventory.dao.PantryDao;
import com.example.crisisfridge.data.inventory.dao.RecipeIngredientsDao;
import com.example.crisisfridge.data.inventory.dao.RecipeDao;
import com.example.crisisfridge.data.inventory.dao.ProductTypeDao;
import com.example.crisisfridge.data.inventory.dao.ShoppingListDao;
import com.example.crisisfridge.data.inventory.entity.FridgeItemEntity;
import com.example.crisisfridge.data.inventory.entity.ProductTypeEntity;
import com.example.crisisfridge.data.inventory.entity.RecipeEntity;
import com.example.crisisfridge.data.inventory.entity.RecipeIngredientEntity;
import com.example.crisisfridge.data.inventory.entity.ShoppingListEntity;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities =
        {
                FridgeItemEntity.class,
                ProductTypeEntity.class,
                RecipeEntity.class,
                RecipeIngredientEntity.class,
                ShoppingListEntity.class,
        },
        version = 1)

public abstract class LocalDB extends RoomDatabase {

    private static final String TAG = "LocalDB";


    public abstract ProductTypeDao getProductTypeDao();
    public abstract RecipeDao getRecipeDao();
    public abstract PantryDao getPantryDao();
    public abstract RecipeIngredientsDao getRecipeIngredientsDao();
    public abstract ShoppingListDao getShoppingListDao();

    private static final String DB_NAME = "crisis_fridge";
    private static LocalDB INSTANCE;

    public static LocalDB getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (LocalDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = createDatabaseInstance(context);
                }
            }
        }
        return INSTANCE;
    }

    private static LocalDB createDatabaseInstance(Context context){
        return Room.databaseBuilder(context.getApplicationContext(), LocalDB.class, DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {

                            ProductTypeDao mapPointDao = INSTANCE.getProductTypeDao();
                            PantryDao edgeDao = INSTANCE.getPantryDao();
                            RecipeDao recipeDao = INSTANCE.getRecipeDao();
                            RecipeIngredientsDao recipeIngredientsDao = INSTANCE.getRecipeIngredientsDao();
                            ShoppingListDao shoppingListDao = INSTANCE.getShoppingListDao();

                        });
                        Log.d(TAG, "onCreate: data loaded into database");
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        Log.d(TAG, "onOpen: database open");
                    }
                })
                .build();
    }

    private static <T> List<T> getEntityListFromJsonFile(Context context, String jsonName, TypeToken<List<T>> typeToken) {

        List<T> list = null;

        try (InputStream inputStream = context.getAssets().open(jsonName)) {
            Gson gson = new Gson();
            Reader reader = new InputStreamReader(inputStream);
            list = gson.fromJson(reader, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Can't load data from file: " + jsonName, e);
        }

        return list;
    }

}

