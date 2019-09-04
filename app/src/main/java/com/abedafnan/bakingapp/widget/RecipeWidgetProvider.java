package com.abedafnan.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.abedafnan.bakingapp.R;
import com.abedafnan.bakingapp.RecipesActivity;
import com.abedafnan.bakingapp.models.Recipe;
import com.google.gson.Gson;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    private static Recipe cLastVisitedRecipe;
    private static String cRecipeName;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

        // Crete an intent to open the RecipesActivity when the widget is clicked
        Intent intent = new Intent(context, RecipesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Launch pending intent when the widget view is clicked
        views.setOnClickPendingIntent(R.id.lv_ingredients, pendingIntent);

        // Get the name of the last visited recipe and set its name it to the title TextView
        SharedPreferences sp = context.getSharedPreferences("bakingapp", Context.MODE_PRIVATE);
        String lastVisitedRecipeString = sp.getString("recipe", "");
        if (!TextUtils.isEmpty(lastVisitedRecipeString)) {
            Gson gson = new Gson();
            cLastVisitedRecipe = gson.fromJson(lastVisitedRecipeString, Recipe.class);
            cRecipeName = cLastVisitedRecipe.getName();
        } else {
            cRecipeName = "No Data Available";
        }

        views.setTextViewText(R.id.tv_recipe_name, cRecipeName);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

