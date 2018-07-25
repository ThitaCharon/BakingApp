package com.example.thita.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thita.bakingapp.Adapter.IngredientsAdapter;
import com.example.thita.bakingapp.Model.Ingredient;

import java.util.List;

public class IngredientFragment extends Fragment {

    public IngredientFragment(){}

    private List<Ingredient> listOfIngredients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        // get ingredientlist from bundle
        listOfIngredients = getArguments().getParcelableArrayList(IngredientsActivity.INGREDIENTS_EXTRA);
        RecyclerView recyclerView = rootView.findViewById(R.id.ingredients_fragment_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        // create IngredientsAdapter
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getActivity(), listOfIngredients);
        recyclerView.setAdapter(ingredientsAdapter);
        ingredientsAdapter.notifyDataSetChanged();

        return rootView;
    }
}
