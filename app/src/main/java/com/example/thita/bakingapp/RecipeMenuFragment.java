package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thita.bakingapp.Data.RecipeApi;
import com.example.thita.bakingapp.Data.RecipeService;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.Adapter.RecipeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeMenuFragment extends Fragment {

    private static final String tag = RecipeMenuFragment.class.getSimpleName();
    public List<Recipe> recipeList = new ArrayList<>();
    private RecipeAdapter recipeAdapter;
//    public static final String RECIPE_LIST_EXTRA = "RECIPE_EXTRA";

    //constructor
    public RecipeMenuFragment(){}

    //Inflates the fragment layout and sets resources
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState){

        //inflate layout
        final View rootView = inflater.inflate(R.layout.fragment_main_menu, container,false);
        final ListView listView = rootView.findViewById(R.id.lv_menu_fragment);

        RecipeService recipyService = RecipeApi.getApi().create(RecipeService.class);
        final Call<List<Recipe>> mCall = recipyService.loadAllRecipeFromServer();
        mCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
//                recipeList.clear();
                recipeList = response.body();
                Log.d("Menu #1 : ", recipeList.get(0).getName());
                Log.d("Menu #2 : ", recipeList.get(1).getName());
                Log.d("Menu #3 : ", recipeList.get(2).getName());
                Log.d("Menu #4 : ", recipeList.get(3).getName());
                Log.d("Fragment", "Retrofit callBack success & built adapter : of size " + recipeList.size());
                recipeAdapter = new RecipeAdapter(recipeList, R.layout.row_item_recipe, getContext());
                listView.setAdapter(recipeAdapter);
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(tag, "Fail callBack success");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Item is clicked at position " + position, Toast.LENGTH_SHORT).show();
                Recipe recipeSelected = (Recipe) parent.getItemAtPosition(position);
                // TODO (2) pass data to RecipeDetailActivity
            }
        });

        return  rootView;
    }

    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList(){
        return recipeList;
    }



}
