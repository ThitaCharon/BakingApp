package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.thita.bakingapp.Fragment.PlayerFragment;
import com.example.thita.bakingapp.Model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity  {

    private String url;
    private String shortdescrip;
    private String descrip;
    private int position = -1;
    private List<Step> stepList;
    private Button next;
    private Button back;
    Bundle args = new Bundle();
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        next = (Button) findViewById(R.id.next_btn);
        back = (Button) findViewById(R.id.back_btn);

        Intent intent = getIntent();
        if (savedInstanceState == null) {
            if (intent != null) {
                position = intent.getExtras().getInt(String.valueOf(R.string.KEY_INDEX_POSIRION));
                stepList = intent.getParcelableArrayListExtra(String.valueOf(R.string.KEY_STEPS_LIST));
            }
        }else{
            position = savedInstanceState.getInt(String.valueOf(R.string.KEY_INDEX_POSIRION), position);
            stepList = savedInstanceState.getParcelableArrayList(String.valueOf(R.string.KEY_STEPS_LIST));
        }

        url = stepList.get(position).getVideoURL();
        shortdescrip = stepList.get(position).getShortDescription();
        descrip = stepList.get(position).getDescription();
        args.putString(String.valueOf(R.string.KEY_VIDEO_URL),url);
        args.putString(String.valueOf(R.string.KEY_SHORT_DESCRIPTION), shortdescrip);
        args.putString(String.valueOf(R.string.KEY_DESCRIPTION), descrip);

        PlayerFragment playerStepsFragment = new PlayerFragment();
        playerStepsFragment.setArguments(args);

        /**
        if (savedInstanceState != null) {
            fragmentManager.beginTransaction().replace(R.id.activity_steps_player_container, playerStepsFragment).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.activity_steps_player_container, playerStepsFragment).commit();
        }
         **/
        // TODO on rotation savedInstanceState is not null and fragmentManger still associated with activity so retrieve current position isn Fragment
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.activity_steps_player_container, playerStepsFragment).commit();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindDataNextStep();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindDataPreviousStep();
            }
        });
        int step = position + 1;
        setTitle("Step " + step);
    }

    private void bindDataNextStep() {
        Toast.makeText(this, "Bind data", Toast.LENGTH_SHORT).show();
        position++;
        if (position == stepList.size()){
            position--;
        }
        url = stepList.get(position).getVideoURL();
        shortdescrip = stepList.get(position).getShortDescription();
        descrip = stepList.get(position).getDescription();
        args.putString(String.valueOf(R.string.KEY_VIDEO_URL),url);
        args.putString(String.valueOf(R.string.KEY_SHORT_DESCRIPTION), shortdescrip);
        args.putString(String.valueOf(R.string.KEY_DESCRIPTION), descrip);
        int step = position + 1;
        setTitle("Step " + step);
        PlayerFragment playerStepsFragment = new PlayerFragment();
        playerStepsFragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.activity_steps_player_container, playerStepsFragment).commit();

    }

    private void bindDataPreviousStep() {
//        Toast.makeText(this, "Bind data", Toast.LENGTH_SHORT).show();
        position--;
        if (position == -1){
            position++;
        }
        url = stepList.get(position).getVideoURL();
        shortdescrip = stepList.get(position).getShortDescription();
        descrip = stepList.get(position).getDescription();
        args.putString(String.valueOf(R.string.KEY_VIDEO_URL),url);
        args.putString(String.valueOf(R.string.KEY_SHORT_DESCRIPTION), shortdescrip);
        args.putString(String.valueOf(R.string.KEY_DESCRIPTION), descrip);

        PlayerFragment playerStepsFragment = new PlayerFragment();
        playerStepsFragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.activity_steps_player_container, playerStepsFragment).commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(String.valueOf(R.string.KEY_INDEX_POSIRION), position);
        outState.putParcelableArrayList(String.valueOf(R.string.KEY_STEPS_LIST), (ArrayList<? extends Parcelable>) stepList);
    }

}
