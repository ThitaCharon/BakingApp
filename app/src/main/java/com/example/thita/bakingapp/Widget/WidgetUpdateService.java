package com.example.thita.bakingapp.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.R;

public class WidgetUpdateService extends IntentService {

    public static final String ACTION_UPDATE_LIST_VIEW = "update_widget_listview";
    public static final String RECIPE_KEY = "target";

    public WidgetUpdateService() {
        super(WidgetUpdateService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent !=null){
            final String action = intent.getAction();
            //check action order if correct the get current recipe and handleAction update listview
            if (ACTION_UPDATE_LIST_VIEW.equals(action)){
                Recipe recipe = intent.getParcelableExtra(RECIPE_KEY);
                handleActionUpdateListView(recipe);
            }
        }
    }

    private void handleActionUpdateListView(Recipe recipe) {
        if (recipe != null) {
            WidgetModel.saveRecipe(this, recipe);
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));

        BakingWidgetProvider.updateAppWidgets(this,appWidgetManager, appWidgetIds);

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_ingredients_list);
    }

    public static void startActionUpdateListView(Context context, Recipe recipe) {
        Intent intent = new Intent(context, WidgetUpdateService.class);
        intent.setAction(WidgetUpdateService.ACTION_UPDATE_LIST_VIEW);
        intent.putExtra(RECIPE_KEY, recipe);
        context.startService(intent);
    }
}
