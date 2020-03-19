package com.example.crisisfridge.data.inventory.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.crisisfridge.data.model.inventory.interfaces.Recipe;

import java.lang.annotation.Annotation;
import java.util.Objects;

@Entity(tableName = "recipe")
public class RecipeEntity implements Recipe {

    @PrimaryKey
    private int recipe_id;
    private String name;
    private String description;

    public RecipeEntity(int recipe_id, String name, String description) {
        this.recipe_id = recipe_id;
        this.name = name;
        this.description = description;
    }


    @Override
    public int getRecipeId() {
        return recipe_id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeEntity that = (RecipeEntity) o;
        return recipe_id == that.recipe_id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe_id, name, description);
    }
}
