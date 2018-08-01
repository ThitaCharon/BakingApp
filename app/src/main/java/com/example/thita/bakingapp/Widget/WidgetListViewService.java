package com.example.thita.bakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.thita.bakingapp.Model.Ingredient;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class WidgetListViewService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetListView(getApplicationContext());
    }
}

class WidgetListView implements RemoteViewsService.RemoteViewsFactory{
    private Context context;
    private List<Ingredient> ingredients = new ArrayList<>();
    private Recipe recipeWidget = null;

    public WidgetListView(Context applicationContext) {
        context = applicationContext;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        recipeWidget = WidgetModel.getWidgetData(context);
        ingredients = recipeWidget.getIngredients();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        String text;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.row_item_widget);
        text = ingredients.get(position).getIngredient();
        Log.d("test" , text);
        remoteViews.setTextViewText(R.id.widget_ingredient_name_tv , ingredients.get(position).getIngredient());
        text = ingredients.get(position).getQuantity() + "";
        remoteViews.setTextViewText(R.id.widget_ingredient_quantities_tv , ingredients.get(position).getQuantity() + "");
        remoteViews.setTextViewText(R.id.widget_ingredient_measure_tv , ingredients.get(position).getMeasure());

        Intent fillInIntent = new Intent();
        fillInIntent.putExtra(String.valueOf(R.string.KEY_RECIPE), recipeWidget);
        remoteViews.setOnClickFillInIntent(R.id.parent_widget_ingredient_view, fillInIntent);
        return remoteViews;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }


    @Override
    public void onDestroy() {
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }



    @Override
    public boolean hasStableIds() {
        return true;
    }
}
