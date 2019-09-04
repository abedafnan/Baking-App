package com.abedafnan.bakingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abedafnan.bakingapp.fragments.RecipeDetailFragment;
import com.abedafnan.bakingapp.fragments.StepDetailsFragment;
import com.abedafnan.bakingapp.models.Recipe;
import com.abedafnan.bakingapp.models.Step;
import com.abedafnan.bakingapp.widget.RecipeWidgetProvider;
import com.google.gson.Gson;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener {

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (findViewById(R.id.step_details_container) != null) {
            twoPane = true;

        } else {
            twoPane = false;
        }

        // Check so it doesn't recreate the fragment on orientation change
        if (savedInstanceState == null) {

            // Receive the intent from The recipes list
            // Launch the recipe details according to the received recipe
            receiveRecipe();
        }

    }

    @Override
    public void onStepClicked(Step step) {

        if (twoPane) {
            StepDetailsFragment newFragment = new StepDetailsFragment();

            Bundle bundle = new Bundle();
            bundle.putString("video_url", step.getVideoURL());
            bundle.putString("thumbnail_url", step.getThumbnailURL());
            bundle.putString("description", step.getDescription());
            newFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.step_details_container, newFragment)
                    .commit();

        } else {
            Intent intent = new Intent(this, StepDetailsActivity.class);
            intent.putExtra("step", step);
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

            // Store the selected recipe in SharedPreferences (to use in the widget)
            SharedPreferences sharedPreferences = getSharedPreferences("bakingapp", MODE_PRIVATE);
            Gson gson = new Gson();
            String recipeString = gson.toJson(currentRecipe);
            sharedPreferences.edit().putString("recipe", recipeString).apply();

            // Refresh the widget when new recipe is opened
            RecipeWidgetProvider.sendUpdateBroadcast(this);

            // Display the RecipeDetailFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.container_recipe_detail, fragment)
                    .commit();

        }
    }
}
