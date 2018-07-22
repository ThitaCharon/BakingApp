package com.example.thita.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thita.bakingapp.Adapter.ListOverviewAdpater;
import com.example.thita.bakingapp.Adapter.RecipeAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeOverviewFragment extends Fragment {
    private static final String tag = RecipeOverviewFragment.class.getSimpleName();
    public final static String OVERVIEW_LIST_EXTRA = "OVERVIEW_EXTRA";
    private ArrayList<String> overviewList = new ArrayList<>();
    private ListOverviewAdpater listOverviewAdpater;

    public RecipeOverviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        final View rootView = inflater.inflate(R.layout.overview_fragment, container, false);
        final ListView listView = rootView.findViewById(R.id.fragment_overview_lv);


        if (saveInstanceState != null){
            overviewList = saveInstanceState.getStringArrayList(OVERVIEW_LIST_EXTRA);
        }else if( getArguments() != null && getArguments().containsKey(RecipeDetailActivity.RECIPE_OVERVIEW_LIST_EXTRA)) {
            overviewList = getArguments().getStringArrayList(RecipeDetailActivity.RECIPE_OVERVIEW_LIST_EXTRA);
        }

        List<String> list = Arrays.asList("Start", "end");
        Log.d(tag, "OverviewList size " + list.size() + "Topice Size " + overviewList.size());
        listOverviewAdpater = new ListOverviewAdpater(overviewList, getContext(), R.layout.row_item_overview);
        listView.setAdapter(listOverviewAdpater);
        listOverviewAdpater.notifyDataSetChanged();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState){
        super.onSaveInstanceState(currentState);
        currentState.putStringArrayList(OVERVIEW_LIST_EXTRA,overviewList);
    }

}
