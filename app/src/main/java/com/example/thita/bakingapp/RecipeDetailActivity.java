package com.example.thita.bakingapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.thita.bakingapp.Model.Ingredient;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.Model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    private Recipe recipe;
    private android.support.v4.app.FragmentManager mFragmentManager;
    public ArrayList<String> overviewList = new ArrayList<>();
    public static final String RECIPE_OVERVIEW_LIST_EXTRA = "OVERVIEW_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_recipe_overview);
        if (savedInstanceState == null){
            Intent targetIntent = getIntent();
            recipe = targetIntent.getParcelableExtra(RecipeActivity.RECIPE_EXTRA);
            setTitle(recipe.getName());
            ArrayList<String> overviewList = recipe.getRecipeOverview();
            overviewList.add("Next step");
            //TODO get steplist decription and add to overviewList but step list always return 0
            List<Step> stepList = recipe.getStepsList();
            List<Ingredient> ingredientList = recipe.getIngredientsList();
            Log.d("StepList", stepList.size() + "" );
            Log.d("ingredientList", ingredientList.size() + "" );

            // pass data to fragment using Bundle
            Bundle args = new Bundle();
            args.putStringArrayList(RECIPE_OVERVIEW_LIST_EXTRA, overviewList);
            RecipeOverviewFragment overviewFragment = new RecipeOverviewFragment();
            overviewFragment.setArguments(args);
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction().add(R.id.recipe_detail_overview,overviewFragment).commit();

        }

    }

}
