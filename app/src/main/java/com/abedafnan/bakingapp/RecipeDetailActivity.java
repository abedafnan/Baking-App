package com.abedafnan.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.abedafnan.bakingapp.models.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        if (intent != null) {
            Recipe currentRecipe = (Recipe) intent.getSerializableExtra("recipe");

            Bundle bundle = new Bundle();
            bundle.putSerializable("recipe", currentRecipe);
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.container_recipe_detail, fragment)
                    .commit();

        }
    }
}
