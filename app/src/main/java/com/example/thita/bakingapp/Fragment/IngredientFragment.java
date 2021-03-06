package com.example.thita.bakingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thita.bakingapp.Adapter.IngredientsAdapter;
import com.example.thita.bakingapp.Model.Ingredient;
import com.example.thita.bakingapp.R;

import java.util.List;

public class IngredientFragment extends Fragment {

    public IngredientFragment(){}
    private List<Ingredient> listOfIngredients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        listOfIngredients = getArguments().getParcelableArrayList(String.valueOf(R.string.KEY_INGREDIENT_LIST));
        RecyclerView recyclerView = rootView.findViewById(R.id.ingredients_fragment_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getActivity(), listOfIngredients);
        recyclerView.setAdapter(ingredientsAdapter);
        ingredientsAdapter.notifyDataSetChanged();

        return rootView;
    }
}
