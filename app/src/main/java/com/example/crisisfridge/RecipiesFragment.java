package com.example.crisisfridge;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crisisfridge.data.model.inventory.interfaces.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecipiesFragment extends Fragment {

    private RecipiesViewModel mViewModel;
    private RecyclerView recipesListRecyclerView;
    private RecipeAdapter recipeAdapter;

    private SearchView searchView;
    private FloatingActionButton addRecipeButton;

    public static RecipiesFragment newInstance() {
        return new RecipiesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipies_fragment, container, false);
        recipesListRecyclerView = rootView.findViewById(R.id.recipesList);
        recipesListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipiesViewModel.class);


        // TODO: Use the ViewModel
    }

    private void updateUI() {
        RecipeStud recipeStud = new RecipeStud("Pieczarki", "błazarki");
        RecipeStud recipeStud2 = new RecipeStud("melon", "ziemniaki");
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipeStud);
        recipes.add(recipeStud2);
        recipes.add(recipeStud2);
        recipes.add(recipeStud2);
        recipeAdapter = new RecipeAdapter(recipes);
        recipesListRecyclerView.setAdapter(recipeAdapter);
    }

    private class RecipesHolder extends RecyclerView.ViewHolder {

        private TextView textRecipeDescription;
        private TextView textRecipeName;
        private ImageView imageRecipe;
        private Recipe mRecipe;

        RecipesHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.list_item_recipe, viewGroup, false));

            textRecipeName = itemView.findViewById(R.id.textRecipeName);
            textRecipeDescription = itemView.findViewById(R.id.textRecipeDescription);
            imageRecipe = itemView.findViewById(R.id.imageRecipe);
            itemView.setOnClickListener(view -> Toast.makeText(getActivity(),
                    mRecipe.getName() + " klik!", Toast.LENGTH_SHORT)
                    .show());

        }

        void bindData(Recipe recipe) {
            mRecipe = recipe;
            textRecipeName.setText(mRecipe.getName());
            textRecipeDescription.setText(mRecipe.getDescription());
        }

    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipesHolder> {
        private List<Recipe> recipeList;

        RecipeAdapter(List<Recipe> recipeList) {
            this.recipeList = recipeList;
        }

        @NonNull
        @Override
        public RecipesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RecipesHolder(layoutInflater, parent);
        }


        @Override
        public void onBindViewHolder(@NonNull RecipesHolder holder, int position) {
            Recipe recipe = recipeList.get(position);
            holder.bindData(recipe);
        }

        @Override
        public int getItemCount() {
            return recipeList.size();
        }

    }

    private class RecipeStud implements Recipe {
        private int id = 0;
        private String name;
        private String description;
        private boolean isPreaparable;

        public RecipeStud(String name, String description) {
            this.name = name;
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
        public String getDescription() {
            return description;
        }
    }

}
