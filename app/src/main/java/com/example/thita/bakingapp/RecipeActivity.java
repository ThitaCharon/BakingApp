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

        loadDataAPI();

        //TODO (1) if configuration change call beginTransaction.replace
        if (getSupportFragmentManager().getFragments().isEmpty() ) {

//            Bundle args = new Bundle();
//            args.putParcelableArrayList(RECIPE_LIST_EXTRA, (ArrayList<? extends Parcelable>) mRecipeList);
//            Log.d(tag, mRecipeList.size()+" SIZE pass");

            RecipeMenuFragment menuFragment = new RecipeMenuFragment();
//            menuFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.menu_container, menuFragment).commit();

        }


//            mRecipeList = menuFragment.getRecipeList();
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
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(RECIPE_LIST_EXTRA, (ArrayList<? extends Parcelable>) mRecipeList);
    }

}
