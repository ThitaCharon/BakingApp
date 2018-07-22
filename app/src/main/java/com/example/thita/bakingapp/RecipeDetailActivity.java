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

    private Recipe recipe ;
    private android.support.v4.app.FragmentManager mFragmentManager;
    public ArrayList<String> overviewList = new ArrayList<>();
    public List<Step> stepsList;
    public List<Ingredient> ingredientList;
    public static final String RECIPE_OVERVIEW_LIST_EXTRA = "OVERVIEW_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_recipe_overview);
        if (savedInstanceState == null){

            Intent intent = getIntent();
            if (intent != null ) {
                // TODO try to get recipe target
                recipe = intent.getExtras(RecipeActivity.RECIPE_EXTRA);

                stepsList = intent.getParcelableArrayListExtra(RecipeActivity.STEP_LIST_EXTRA);
                ingredientList = intent.getParcelableArrayListExtra(RecipeActivity.INGREDIENT_LIST_EXTRA);
                for (int i = 0; i < stepsList.size(); i++) {
                    overviewList.add(stepsList.get(i).getShortDescription());
                }
                setTitle(recipe.getName());

            }
            Bundle args = new Bundle();
            args.putStringArrayList(RECIPE_OVERVIEW_LIST_EXTRA, overviewList);
            RecipeOverviewFragment overviewFragment = new RecipeOverviewFragment();
            overviewFragment.setArguments(args);
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction().add(R.id.recipe_detail_overview,overviewFragment).commit();

        }

    }

}
