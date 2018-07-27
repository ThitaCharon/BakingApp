package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.thita.bakingapp.Data.RecipeApi;
import com.example.thita.bakingapp.Data.RecipeService;
import com.example.thita.bakingapp.Model.Ingredient;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.Model.Step;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity implements RecipeMenuFragment.RecipeMenuFragListener {


    public List<Recipe> mRecipeList = new ArrayList<>();
    public static final String tag = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        if (getSupportFragmentManager().getFragments().isEmpty() ) {

            RecipeMenuFragment menuFragment = new RecipeMenuFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.menu_container, menuFragment).commit();
        }
        loadDataAPI();

    }


    private void loadDataAPI(){
        RecipeService recipyService = RecipeApi.getApi().create(RecipeService.class);
        final Call<List<Recipe>> mCall = recipyService.loadAllRecipeFromServer();
        mCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipeList.clear();
                mRecipeList = response.body();
                RecipeMenuFragment menuFragment = new RecipeMenuFragment();
                Bundle args = new Bundle();
                args.putParcelableArrayList(String.valueOf(R.string.KEY_RECIPE_LIST), (ArrayList<? extends Parcelable>) mRecipeList);
                menuFragment.setArguments(args);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.menu_container, menuFragment).commit();

            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(tag, "Fail callBack success");
            }
        });
    }

    @Override
    public void recipeItemClick(Recipe recipeClicked) {

        if (recipeClicked != null){
            List<Step> stepList = recipeClicked.getSteps();
            List<Ingredient> ingredientList = recipeClicked.getIngredients();
            Intent intentOverview = new Intent(getApplicationContext(), RecipeOverviewActivity.class);
            intentOverview.putExtra(String.valueOf(R.string.KEY_RECIPE), recipeClicked);
//            intentOverview.putExtra(String.valueOf(R.string.KEY_NAME),recipeClicked.getName());
//            intentOverview.putParcelableArrayListExtra(String.valueOf(R.string.KEY_INGREDIENT_LIST), (ArrayList<? extends Parcelable>) ingredientList);
//            intentOverview.putParcelableArrayListExtra(String.valueOf(R.string.KEY_STEPS_LIST), (ArrayList<? extends Parcelable>) stepList);
            startActivity(intentOverview);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(String.valueOf(R.string.KEY_RECIPE_LIST), (ArrayList<? extends Parcelable>) mRecipeList);
    }

}
