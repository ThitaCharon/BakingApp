package com.example.thita.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thita.bakingapp.Model.Step;

import java.util.List;

public class DescriptionFragment extends Fragment {

    public static String NAME = "NAME";
    public static String DESCRIPTION = "DESCRIPTION";
    public static String SHORT_DESCRIPTION = "SHORT_DESCRIPTION";
    public String name;
    public String shortDescription;
    public String description;
    // Store instance variables
    private String title;
    private int page;
    private Step mStep;
    private final static String STEP_KEY = "step_key";



    public DescriptionFragment(){}

    // newInstance constructor for creating fragment with arguments
    public static DescriptionFragment newInstance(Step step) {
        Bundle args = new Bundle();
        args.putParcelable(STEP_KEY, step);

        DescriptionFragment desfragment = new DescriptionFragment();
        desfragment.setArguments(args);
        return desfragment;
    }

    // Store instance variables based on arguments passed
    /**
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }
     **/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_description,container,false);

        Bundle args = getArguments();
        if (args == null){throw new AssertionError();}

        name = getArguments().getString(NAME);
//        shortDescription = getArguments().getString(SHORT_DESCRIPTION);
//        description = getArguments().getString(DESCRIPTION);

        mStep = args.getParcelable(STEP_KEY);

        if (mStep == null){
            throw new AssertionError();
        }
        TextView tvName = rootView.findViewById(R.id.short_description_tv);
        tvName.setText(name);
        TextView tvShortDesc = rootView.findViewById(R.id.short_description_tv);
        tvShortDesc.setText(mStep.getShortDescription());
        TextView tvDesc = rootView.findViewById(R.id.description_tv);
        tvDesc.setText(mStep.getDescription());

        return rootView;
    }
}
