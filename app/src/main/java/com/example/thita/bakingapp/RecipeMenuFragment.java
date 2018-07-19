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

        Bundle args = getArguments();
        recipeList = args.getParcelableArrayList(RecipeActivity.RECIPE_LIST_EXTRA);
        Log.d(tag,"Size is : ...." + recipeList.isEmpty());
        recipeAdapter = new RecipeAdapter(recipeList, R.layout.row_item_recipe, getContext());
        listView.setAdapter(recipeAdapter);
        recipeAdapter.notifyDataSetChanged();

        return  rootView;
    }

    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }





}
