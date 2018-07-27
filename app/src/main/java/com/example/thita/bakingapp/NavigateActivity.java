package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.thita.bakingapp.Model.Step;

import java.util.List;

public class NavigateActivity extends FragmentActivity {


//    public String Url;
//    public String shortdescrip;
//    public String descrip;
    FragmentManager fragmentManager;
    public List<Step> steps;
    public int position;
    public static String POSITION = "POSITION";
    public static String STEPS = "STEPS";

    private ViewPager mPager;
    private PagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);

        fragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        if (intent != null) {
            steps = intent.getParcelableArrayListExtra(RecipeActivity.STEP_LIST_EXTRA);
            position = intent.getExtras().getInt(POSITION);
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        Button back = (Button) findViewById(R.id.back_btn);
        Button next = (Button) findViewById(R.id.next_btn);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);



    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DescriptionFragment.newInstance(steps.get(position));
        }

        @Override
        public int getCount() {
            return steps.size();
        }
    }

}

