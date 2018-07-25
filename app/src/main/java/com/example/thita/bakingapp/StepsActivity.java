package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.Model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity {

    public int pos;
    public Recipe sRecipe;
    public List<Step> stepList;
    public String Url;

    public static final String POSITION = "POSITION";
    public static final String STEPS_LIST = "STEP_LIST";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Intent intent = getIntent();
        if (intent != null) {

            pos = intent.getExtras().getInt(POSITION);
            sRecipe = intent.getExtras().getParcelable(RecipeOverviewActivity.RECIPE);
            stepList = intent.getParcelableArrayListExtra(RecipeOverviewActivity.STEPS_LIST);
            Url = stepList.get(pos).getVideoURL();

            Bundle args = new Bundle();
            args.putString(PlayerFragment.NAME,sRecipe.getName());
            args.putString(PlayerFragment.VEDIO_URL,Url);

            // TODO Populate Video Player
            PlayerFragment playerStepsFragment = new PlayerFragment();
            playerStepsFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.activity_steps_player_container,playerStepsFragment).commit();

        }
    }
}
