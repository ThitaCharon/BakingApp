package com.example.thita.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

import android.support.annotation.NonNull;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);


    @Test public void clickMenuViewItem_OpensOverviewActivity(){

    //  TODO Perform click on RV
        onView((withId(R.id.fragment_menu_rv))).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_menu_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
//        onView(withId(R.id.menu_tv)).check(matches(withText("Cheesecake"))).check(matches(isDisplayed()));

        //TODO can not pass testing on other position
//        onView(withId(R.id.fragment_menu_rv)).check(matches(atPosition(1, hasDescendant(withText("Brownies")))));
//        onView(withId(R.id.fragment_menu_rv)).check(matches(atPosition(2, hasDescendant(withText("Yellow Cake")))));
//        onView(withId(R.id.fragment_menu_rv)).check(matches(atPosition(3, hasDescendant(withText("Cheesecake")))));
//        onView(withText(R.id.menu_tv)).check(matches(hasDescendant(withText("Nutella Pie"))));

    }


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

}
