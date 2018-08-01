package com.example.thita.bakingapp;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);

    @Test // clicked on the proper recipe
    public void clickMenuViewItem_OpensOverviewActivity() {
        //1.Find the View
        //2.Perform action
        onData(anything()).inAdapterView(withId(R.id.lv_menu_fragment)).atPosition(0).perform(click());
        //3.Check expected result
        onData(anything()).inAdapterView(withId(R.id.fragment_overview_lv)).atPosition(0).check(matches(isDisplayed()));
    }
}
