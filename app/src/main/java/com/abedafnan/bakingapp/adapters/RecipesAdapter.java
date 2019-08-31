package com.abedafnan.bakingapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abedafnan.bakingapp.R;
import com.abedafnan.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    private List<Recipe> mRecipes;
    private OnItemClickHandler mClickHandler;

    public RecipesAdapter(List<Recipe> recipes, OnItemClickHandler clickHandler) {
        mClickHandler = clickHandler;
        mRecipes = recipes;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_recipe, viewGroup, false);

        final RecipesViewHolder viewHolder = new RecipesViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickHandler.onItemClick(viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder recipesViewHolder, int i) {
        Recipe recipe = mRecipes.get(i);

        recipesViewHolder.recipeName.setText(recipe.getName());

        String imageUrl = recipe.getImage();
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get().load(imageUrl)
                    .into(recipesViewHolder.recipeImage);
        } else {
            recipesViewHolder.recipeImage.setImageResource(R.drawable.cupcake);
        }
    }

    @Override
    public int getItemCount() {

        if (mRecipes != null) {
            return mRecipes.size();
        }
        return 0;
    }

    public interface OnItemClickHandler {
        void onItemClick(int position);
    }

    class RecipesViewHolder extends RecyclerView.ViewHolder {

        ImageView recipeImage;
        TextView recipeName;

        RecipesViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeImage = itemView.findViewById(R.id.imageView_recipe);
            recipeName = itemView.findViewById(R.id.textView_recipe);
        }
    }
}
