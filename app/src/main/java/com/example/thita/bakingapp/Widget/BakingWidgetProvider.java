package com.example.thita.bakingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.RemoteViews;

import com.example.thita.bakingapp.IngredientsActivity;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.R;
import com.example.thita.bakingapp.RecipeActivity;
import com.example.thita.bakingapp.RecipeOverviewActivity;
import com.example.thita.bakingapp.StepsActivity;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // get data
        Recipe recipe = WidgetModel.getWidgetData(context);
        CharSequence widgerRecipeName = recipe.getName();

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_provider);
        views.setTextViewText(R.id.widget_title_name, widgerRecipeName);

        //call ListView service
        Intent intentListViewService = new Intent(context, WidgetListViewService.class);
        views.setRemoteAdapter(R.id.widget_ingredients_list,intentListViewService);

        // get data and respond onClick to StepActivity at first step
        Intent intentOnClick = new Intent(context, StepsActivity.class);
        intentOnClick.putParcelableArrayListExtra(String.valueOf(R.string.KEY_STEPS_LIST), (ArrayList<? extends Parcelable>) recipe.getSteps());
        intentOnClick.putExtra(String.valueOf(R.string.KEY_INDEX_POSIRION), 0);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intentOnClick, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_baking_app, pendingIntent);

        Intent appIntent = new Intent(context, RecipeOverviewActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_ingredients_list_ll, appPendingIntent);
        views.setEmptyView(R.id.widget_ingredients_list_ll, R.id.space_tv);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateAppWidgets (Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        WidgetUpdateService.startActionUpdateListView(context,null);
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

