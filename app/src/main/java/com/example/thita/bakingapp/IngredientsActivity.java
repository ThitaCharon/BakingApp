package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.thita.bakingapp.Model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity {

    public static final String INGREDIENTS_EXTRA = "INGREDIENTS_EXTRA";
    public List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intent = getIntent();
        if (intent != null){
            ingredientList = intent.getParcelableArrayListExtra(RecipeOverviewActivity.INGREDIENTS_LIST);
            IngredientFragment ingredientFragment = new IngredientFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList(INGREDIENTS_EXTRA, (ArrayList<? extends Parcelable>) ingredientList);
            ingredientFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.ingredients_container,ingredientFragment).commit();

        }
    }

}
