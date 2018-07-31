package com.example.thita.bakingapp.Widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.thita.bakingapp.Model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WidgetModel {
    public static String KEY_WIDGET_DATA = "DATA";

    public static Recipe getWidgetData(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(KEY_WIDGET_DATA, null);
        Type type = new TypeToken<Recipe>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void saveRecipe(Context context, Recipe recipe) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        prefsEditor.putString(KEY_WIDGET_DATA, json);
        prefsEditor.commit();
    }
}
