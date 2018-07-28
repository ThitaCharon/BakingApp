package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


public class StepsActivity extends AppCompatActivity {

    private String url;
    private String shortdescrip;
    private String descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);


        Intent intent = getIntent();
        if (savedInstanceState == null) {
            if (intent != null) {
                url =intent.getStringExtra(String.valueOf(R.string.KEY_VIDEO_URL));
                shortdescrip = intent.getStringExtra(String.valueOf(R.string.KEY_SHORT_DESCRIPTION));
                descrip = intent.getStringExtra(String.valueOf(R.string.KEY_DESCRIPTION));
            }
        }else{
            url = savedInstanceState.getString(String.valueOf(R.string.KEY_VIDEO_URL));
            shortdescrip = savedInstanceState.getString(String.valueOf(R.string.KEY_SHORT_DESCRIPTION));
            descrip = savedInstanceState.getString(String.valueOf(R.string.KEY_DESCRIPTION));
        }

        Bundle args = new Bundle();
        args.putString(String.valueOf(R.string.KEY_VIDEO_URL),url);
        args.putString(String.valueOf(R.string.KEY_SHORT_DESCRIPTION), shortdescrip);
        args.putString(String.valueOf(R.string.KEY_DESCRIPTION), descrip);

        FragmentManager fragmentManager = getSupportFragmentManager();
        PlayerFragment playerStepsFragment = new PlayerFragment();
        playerStepsFragment.setArguments(args);

        if (savedInstanceState != null) {
            fragmentManager.beginTransaction().replace(R.id.activity_steps_player_container, playerStepsFragment).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.activity_steps_player_container, playerStepsFragment).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(String.valueOf(R.string.KEY_VIDEO_URL), url);
        outState.putString(String.valueOf(R.string.KEY_SHORT_DESCRIPTION), shortdescrip);
        outState.putString(String.valueOf(R.string.KEY_DESCRIPTION), descrip);
    }

}
