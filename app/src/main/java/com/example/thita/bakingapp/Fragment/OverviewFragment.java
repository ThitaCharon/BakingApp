package com.example.thita.bakingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thita.bakingapp.Adapter.OverviewRVAdapter;
import com.example.thita.bakingapp.R;

import java.util.ArrayList;

public class OverviewFragment extends Fragment {
    private static final String tag = OverviewFragment.class.getSimpleName();
    private ArrayList<String> overviewList = new ArrayList<>();
    private OverviewFragListerner mCallback;

    public OverviewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        final RecyclerView recyclerView = rootView.findViewById(R.id.fragment_overview_rv);



        if (saveInstanceState != null){
            overviewList = saveInstanceState.getStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST));
        }else if( getArguments() != null && getArguments().containsKey(String.valueOf(R.string.KEY_OVERVIEW_LIST))) {
            overviewList = getArguments().getStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST));
        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        OverviewRVAdapter overviewRVAdapter = new OverviewRVAdapter(getActivity(),(OverviewRVAdapter.ItemClickedListener) getContext() ,overviewList);
        recyclerView.setAdapter(overviewRVAdapter);
        overviewRVAdapter.notifyDataSetChanged();

        return rootView;

        // TODO respond on item clicked Handle on Listview
        /**
        lisview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCallback != null){
                    mCallback.overviewItemClicked(position);
                }else{
                    throw new UnsupportedOperationException("Callback is currently null, this exception should not have occurred."); }
            }});
        **/
    }

/**
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OverviewFragListerner) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException("The actual class is " + context.getClass().getName()
                    + " but requires a RecipeMenuFragListener implementation."); }
    }
**/
    public interface OverviewFragListerner{
        public void overviewItemClicked(int postion);
    }

    @Override
    public void onSaveInstanceState(Bundle currentState){
        super.onSaveInstanceState(currentState);
        currentState.putStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST),overviewList);
    }

}
