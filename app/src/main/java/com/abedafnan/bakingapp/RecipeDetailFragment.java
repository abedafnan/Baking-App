package com.abedafnan.bakingapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abedafnan.bakingapp.adapters.IngredientsAdapter;
import com.abedafnan.bakingapp.adapters.StepsAdapter;
import com.abedafnan.bakingapp.models.Recipe;

public class RecipeDetailFragment extends Fragment implements StepsAdapter.OnItemClickHandler {

    private RecyclerView mIngredientsRecyclerView;
    private RecyclerView mStepsRecyclerView;
    private Recipe mRecipe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        if (getArguments() != null) {
            mRecipe = (Recipe) getArguments().getSerializable("recipe");
            setUpTheRecyclers(rootView);
        }

        return rootView;
    }

    public void setUpTheRecyclers(View rootView) {
        mIngredientsRecyclerView = rootView.findViewById(R.id.rv_ingredients);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mIngredientsRecyclerView.setAdapter(new IngredientsAdapter(mRecipe.getIngredients()));

        mStepsRecyclerView = rootView.findViewById(R.id.rv_steps);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStepsRecyclerView.setAdapter(new StepsAdapter(mRecipe.getSteps(), this));
    }

    @Override
    public void onItemClick(int position) {

    }
}
