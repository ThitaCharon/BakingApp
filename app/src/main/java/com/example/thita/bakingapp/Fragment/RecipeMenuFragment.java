package com.example.thita.bakingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thita.bakingapp.Adapter.MenuRVAdapter;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeMenuFragment extends Fragment {

    private static final String tag = RecipeMenuFragment.class.getSimpleName();
    public List<Recipe> recipeList = new ArrayList<>();
    private MenuRVAdapter recipeAdapter;
//    private Bundle args = getArguments();
//    private RecipeAdapter recipeAdapters;
//    private RecipeMenuFragListener mCallback = null;

    //constructor
    public RecipeMenuFragment(){}

    //Inflates the fragment layout and sets resources
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState){

        //inflate layout
//        final View rootView = inflater.inflate(R.layout.fragment_main_menu, container,false);
//        final ListView listView = rootView.findViewById(R.id.lv_menu_fragment);
        final View rootView = inflater.inflate(R.layout.fragment_menu_rv, container,false);
        final RecyclerView mRecyclerView = rootView.findViewById(R.id.fragment_menu_rv);

        Bundle args = getArguments();
        if (args != null && args.containsKey(String.valueOf(R.string.KEY_RECIPE_LIST))) {
            recipeList = args.getParcelableArrayList(String.valueOf(R.string.KEY_RECIPE_LIST));
        }
        else {
            recipeList = new ArrayList<Recipe>();
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        recipeAdapter = new MenuRVAdapter(getActivity(), (MenuRVAdapter.ItemClickedListener) getContext(),recipeList);
        mRecyclerView.setAdapter(recipeAdapter);
        mRecyclerView.setHasFixedSize(true);
        recipeAdapter.notifyDataSetChanged();

        /**
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull AdapterView<?> parent,@NonNull View view, int position, long id) {
                Recipe recipeSelect = (Recipe) parent.getItemAtPosition(position);
                if (mCallback != null){
                    mCallback.recipeItemClick(recipeSelect);
                }else{
                    throw new UnsupportedOperationException("Callback is currently null, this exception should not have occurred."); } }
            });

        **/
        return  rootView;
    }


/**
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
**/

}
