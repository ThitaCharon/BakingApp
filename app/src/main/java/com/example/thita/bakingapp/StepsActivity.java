package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class StepsActivity extends AppCompatActivity {

    public String Url;
    public String shortdescrip;
    public String descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Intent intent = getIntent();
        if (intent != null) {
            Url = intent.getExtras().getString(PlayerFragment.VEDIO_URL);
            shortdescrip = intent.getExtras().getString(DescriptionFragment.SHORT_DESCRIPTION);
            descrip = intent.getExtras().getString(DescriptionFragment.DESCRIPTION);
        }
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle args = new Bundle();
            args.putString(PlayerFragment.VEDIO_URL,Url);
            args.putString(DescriptionFragment.NAME,"Name");
            args.putString(DescriptionFragment.SHORT_DESCRIPTION, shortdescrip);
            args.putString(DescriptionFragment.DESCRIPTION, descrip);

            // TODO Populate Video Player
            PlayerFragment playerStepsFragment = new PlayerFragment();
            playerStepsFragment.setArguments(args);
            fragmentManager.beginTransaction().add(R.id.activity_steps_player_container,playerStepsFragment).commit();

            DescriptionFragment descriptionFragment = new DescriptionFragment();
            descriptionFragment.setArguments(args);
            fragmentManager.beginTransaction().add(R.id.activity_steps_instruction_container, descriptionFragment).commit();
    }

}
