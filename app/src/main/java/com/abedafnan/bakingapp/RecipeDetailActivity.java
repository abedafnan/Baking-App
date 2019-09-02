package com.abedafnan.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abedafnan.bakingapp.fragments.RecipeDetailFragment;
import com.abedafnan.bakingapp.fragments.StepDetailsFragment;
import com.abedafnan.bakingapp.models.Recipe;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener {

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // Receive the intent from The recipes list
        // Launch the recipe details according to the received recipe
        receiveRecipe();

        if (findViewById(R.id.step_details_container) != null) {
            twoPane = true;

            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.step_details_container, stepDetailsFragment)
                    .commit();

        } else {
            twoPane = false;
        }
    }

    @Override
    public void onStepClicked(int position, Recipe recipe) {

        if (twoPane) {
            StepDetailsFragment newFragment = new StepDetailsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.step_details_container, newFragment)
                    .commit();

        } else {
            Intent intent = new Intent(this, StepDetailsActivity.class);
            intent.putExtra("step", recipe.getSteps().get(position));
            startActivity(intent);
        }
    }

    public void receiveRecipe() {
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
