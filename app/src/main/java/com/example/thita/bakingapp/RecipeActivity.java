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
    public static final String RECIPE_LIST_EXTRA = "RECIPE_LIST_EXTRA";
    public static final String RECIPE_EXTRA = "RECIPE_EXTRA";
    public static final String STEP_LIST_EXTRA = "STEP_LIST_EXTRA";
    public static final String INGREDIENT_LIST_EXTRA = "RECIPE_LIST_EXTRA";
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
                Log.d("Menu #1 : ", mRecipeList.get(0).getName());
                Log.d("Menu #2 : ", mRecipeList.get(1).getName());
                Log.d("Menu #3 : ", mRecipeList.get(2).getName());
                Log.d("Menu #4 : ", mRecipeList.get(3).getName());
                Log.d("Fragment", "Retrofit callBack success & built adapter : of size " + mRecipeList.size());


                RecipeMenuFragment menuFragment = new RecipeMenuFragment();
                Bundle args = new Bundle();
                args.putParcelableArrayList(RECIPE_LIST_EXTRA, (ArrayList<? extends Parcelable>) mRecipeList);
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
            Toast.makeText(this, "Recipe selected name : " + recipeClicked.getName(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), RecipeOverviewActivity.class);
            intent.putExtra(RECIPE_EXTRA, recipeClicked);
            intent.putParcelableArrayListExtra(INGREDIENT_LIST_EXTRA, (ArrayList<? extends Parcelable>) ingredientList);
            intent.putParcelableArrayListExtra(STEP_LIST_EXTRA, (ArrayList<? extends Parcelable>) stepList);
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(RECIPE_LIST_EXTRA, (ArrayList<? extends Parcelable>) mRecipeList);
    }

}
