package com.example.thita.bakingapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.Adapter.RecipeAdapter;
import com.example.thita.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

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
//        final RecyclerView recyclerView = rootView.findViewById(R.id.rv_menu_fragment);

        Bundle args = getArguments();
        if (args != null && args.containsKey(String.valueOf(R.string.KEY_RECIPE_LIST))) {
            recipeList = args.getParcelableArrayList(String.valueOf(R.string.KEY_RECIPE_LIST));
        }
        else {
            recipeList = new ArrayList<Recipe>();
        }

        recipeAdapter = new RecipeAdapter(recipeList, R.layout.row_item_recipe, getContext());
        listView.setAdapter(recipeAdapter);
        recipeAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull AdapterView<?> parent,@NonNull View view, int position, long id) {
                Recipe recipeSelect = (Recipe) parent.getItemAtPosition(position);
                if (mCallback != null){
                    mCallback.recipeItemClick(recipeSelect);
                }else{
                    throw new UnsupportedOperationException("Callback is currently null, this exception should not have occurred."); } }
            });

        return  rootView;
    }


    public interface RecipeMenuFragListener {
        void recipeItemClick(Recipe recipeClicked);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RecipeMenuFragListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException("The actual class is " + context.getClass().getName() + " but requires a RecipeMenuFragListener implementation."); }
    }


}
