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

    public List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intent = getIntent();
        if (intent != null){
            ingredientList = intent.getParcelableArrayListExtra(String.valueOf(R.string.KEY_INGREDIENT_LIST));
            Bundle args = new Bundle();
            args.putParcelableArrayList(String.valueOf(R.string.KEY_INGREDIENT_LIST), (ArrayList<? extends Parcelable>) ingredientList);
            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.ingredients_container,ingredientFragment).commit();

        }
    }

}
