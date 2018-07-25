package com.example.thita.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {

    public static String NAME = "NAME";
    public static String DESCRIPTION = "DESCRIPTION";
    public static String SHORT_DESCRIPTION = "SHORT_DESCRIPTION";
    public String name;
    public String shortDescription;
    public String description;
    public DescriptionFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_description,container,false);

        Bundle args = getArguments();
        if (args != null){
            name = getArguments().getString(NAME);
            shortDescription = getArguments().getString(SHORT_DESCRIPTION);
            description = getArguments().getString(DESCRIPTION);
        }

        TextView tvName = rootView.findViewById(R.id.short_description_tv);
        tvName.setText(name);
        TextView tvShortDesc = rootView.findViewById(R.id.short_description_tv);
        tvShortDesc.setText(shortDescription);
        TextView tvDesc = rootView.findViewById(R.id.description_tv);
        tvDesc.setText(description);

        return rootView;
    }
}
