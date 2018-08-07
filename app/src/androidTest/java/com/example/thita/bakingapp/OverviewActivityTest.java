package com.example.thita.bakingapp;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;

import android.support.annotation.NonNull;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import static android.support.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
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

//        onView(withId(R.id.fragment_overview_rv)).check(matches(isDisplayed()));


    }

    /**

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
     **/

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

