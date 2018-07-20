package com.example.thita.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private RecipeMenuFragListener mCallback = null;

    //constructor
    public RecipeMenuFragment(){}

    //Inflates the fragment layout and sets resources
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState){

        //inflate layout
        final View rootView = inflater.inflate(R.layout.fragment_main_menu, container,false);
        final ListView listView = rootView.findViewById(R.id.lv_menu_fragment);

        Bundle args = getArguments();
//        recipeList = args.getParcelableArrayList(RecipeActivity.RECIPE_LIST_EXTRA);
        if (args != null && args.containsKey(RecipeActivity.RECIPE_LIST_EXTRA)) {
            recipeList = args.getParcelableArrayList(RecipeActivity.RECIPE_LIST_EXTRA);
        }
        else {
            recipeList = new ArrayList<Recipe>();
        }
        Log.d(tag,"Recipe : " + recipeList.isEmpty() +recipeList.size());
        recipeAdapter = new RecipeAdapter(recipeList, R.layout.row_item_recipe, getContext());
        listView.setAdapter(recipeAdapter);
        recipeAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull AdapterView<?> parent,@NonNull View view, int position, long id) {
                Toast.makeText(getContext(), "Clicked at position " + position, Toast.LENGTH_LONG).show();
                Recipe recipeSelect = (Recipe) parent.getItemAtPosition(position);
                if (mCallback == null){
                    // TODO 1 pass the recipeSelect  get error it might be null
                    mCallback.recipeItemClick(recipeSelect);
                }else{
                    throw new UnsupportedOperationException(
                            "Callback is currently null, this exception should not have occurred."
                    );
                }
            }
        });

        return  rootView;
    }

    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }


    public interface RecipeMenuFragListener {
        public void recipeItemClick(Recipe recipeClicked);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RecipeMenuFragListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(
                    "The actual class is " +
                            context.getClass().getName() +
                            " but requires a RecipeMenuFragListener implementation."
            );
        }
    }





}
