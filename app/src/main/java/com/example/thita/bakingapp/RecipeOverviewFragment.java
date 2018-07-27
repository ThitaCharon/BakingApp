package com.example.thita.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thita.bakingapp.Adapter.ListOverviewAdpater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeOverviewFragment extends Fragment {
    private static final String tag = RecipeOverviewFragment.class.getSimpleName();
//    public final static String OVERVIEW_LIST_EXTRA = "OVERVIEW_EXTRA";
    private ArrayList<String> overviewList = new ArrayList<>();
    private ListOverviewAdpater listOverviewAdpater;
    private OverviewFragListerner mCallback;


    public RecipeOverviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        final View rootView = inflater.inflate(R.layout.overview_fragment, container, false);
        final ListView listView = rootView.findViewById(R.id.fragment_overview_lv);


        if (saveInstanceState != null){
            overviewList = saveInstanceState.getStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST));
        }else if( getArguments() != null && getArguments().containsKey(String.valueOf(R.string.KEY_OVERVIEW_LIST))) {
            overviewList = getArguments().getStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST));
        }

        List<String> list = Arrays.asList("Start", "end");
        Log.d(tag, "OverviewList size " + list.size() + "Topice Size " + overviewList.size());
        listOverviewAdpater = new ListOverviewAdpater(overviewList, getContext(), R.layout.row_item_overview);
        listView.setAdapter(listOverviewAdpater);
        listOverviewAdpater.notifyDataSetChanged();

        // respond on item clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCallback != null){
                    mCallback.overviewItemClicked(position);
                }else{
                    throw new UnsupportedOperationException(
                            "Callback is currently null, this exception should not have occurred."
                    );
                }
            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OverviewFragListerner) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(
                    "The actual class is " +
                            context.getClass().getName() +
                            " but requires a RecipeMenuFragListener implementation."
            );
        }
    }

    public interface OverviewFragListerner{
        public void overviewItemClicked(int postion);
    }
    @Override
    public void onSaveInstanceState(Bundle currentState){
        super.onSaveInstanceState(currentState);
        currentState.putStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST),overviewList);
    }

}
