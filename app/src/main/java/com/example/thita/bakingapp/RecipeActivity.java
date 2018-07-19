package com.example.thita.bakingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thita.bakingapp.Adapter.RecipeAdapter;
import com.example.thita.bakingapp.Data.RecipeApi;
import com.example.thita.bakingapp.Data.RecipeService;
import com.example.thita.bakingapp.Model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {


    public List<Recipe> mRecipeList = new ArrayList<>();
    public static final String RECIPE_LIST_EXTRA = "RECIPE_EXTRA";
    public static final String tag = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //TODO (1) if configuration change call beginTransaction.replace
        RecipeMenuFragment menuFragment = new RecipeMenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.menu_container, menuFragment).commit();
        mRecipeList = menuFragment.getRecipeList();



    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        RecipeMenuFragment menuFragment = new RecipeMenuFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.menu_container,menuFragment).commit();
//
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(RECIPE_LIST_EXTRA, (ArrayList<? extends Parcelable>) mRecipeList);
    }

}
