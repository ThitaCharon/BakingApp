package com.example.thita.bakingapp;

import static android.support.test.espresso.Espresso.onView;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static android.support.test.espresso.action.ViewActions.click;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class OverviewActivityTest {
    @Rule public ActivityTestRule<OverviewActivity> mOverviewTestRule = new ActivityTestRule<>(OverviewActivity.class);

    @Test // clicked on the proper recipe
    public void clickOverviewItem_OpensIngredientsActivity() {
        /** Testing on ListView
        onData(anything()).inAdapterView(withId(R.id.fragment_overview_lv)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.ingredients_fragment_rv)).atPosition(0).check(matches(isDisplayed()));
         **/

//        onData(anything()).inAdapterView(allOf(withId(R.id.rv_overview_list),));

//        onView(withId(R.id.fragment_overview_rv)).perform(RecyclerViewActions.scrollToPosition(3));

    }
    // Convenience helper
    /**
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
     **/

    /**
     private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

     return new TypeSafeMatcher<View>() {
    @Override
    public void describeTo(Description description) {
    description.appendText("Child at position " + position + " in parent ");
    parentMatcher.describeTo(description);
    }

    @Override
    public boolean matchesSafely(View view) {
    ViewParent parent = view.getParent();
    return parent instanceof ViewGroup && parentMatcher.matches(parent)
    && view.equals(((ViewGroup) parent).getChildAt(position));
    }
    };
     }
    **/
}
