package com.example.thita.bakingapp;

import static android.support.test.espresso.Espresso.onView;
import android.support.test.espresso.matcher.ViewMatchers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RecipeOverviewActivityTest {
    @Rule public ActivityTestRule<RecipeOverviewActivity> mOverviewTestRule = new ActivityTestRule<>(RecipeOverviewActivity.class);

    @Test // clicked on the proper recipe
    public void clickOverviewItem_OpensIngredientsActivity() {

        onData(anything()).inAdapterView(withId(R.id.fragment_overview_lv)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.ingredients_fragment_rv)).atPosition(0).check(matches(isDisplayed()));
    }
}
