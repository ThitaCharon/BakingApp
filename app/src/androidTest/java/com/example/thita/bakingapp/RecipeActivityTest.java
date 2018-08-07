package com.example.thita.bakingapp;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
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

        // TODO Perform click on menu (LISTVIEW)
//        onView(withRecyclerView(R.id.lv_menu_fragment).atPosition(0))
//                .check(matches(hasDescendant(withText(“Test”))));x
        onView(withId(R.id.lv_menu_fragment)).check(matches(not(isDisplayed())));
        onData(anything()).inAdapterView(withId(R.id.lv_menu_fragment)).atPosition(0).perform(click());
//        onView(withId(R.id.lv_menu_fragment)).check(matches(not(isDisplayed())));
//        onData(anything()).inAdapterView(withId(R.id.lv_menu_fragment)).atPosition(0).perform(click());
        // TODO check on overview list (RecycleView) display data correctly
        /*
        onData(anything())
                .inAdapterView(allOf(withId(R.id.fragment_overview_rv), withText("Nutella Pie Ingredients"),
                        atPosition(0, withId(R.id.row_overview_tv)))).atPosition(0);
*/
        //        onView(withId(R.id.fragment_overview_rv)).check(matches(not(isDisplayed())));
//        onView(withId(R.id.fragment_overview_rv)).check(matches(atPosition(0,withText("Nutella Pie"))));


//        onView(withId(R.id.fragment_overview_rv)).check(matches(isDisplayed()));
//        onView(withId(R.id.fragment_overview_rv)).check(matches(atPosition(0,withText("Nutella Pie Ingredients"))));

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

    /**
    private static Matcher<View> childAtPosition(final int position, final Matcher<View> parentMatcher) {

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

    /**
    @Test // clicked on the proper recipe
    public void clickMenuViewItem_OpensOverviewActivity() {
        //1.Find the View
        //2.Perform action
        onData(anything()).inAdapterView(withId(R.id.lv_menu_fragment)).atPosition(0).perform(click());
        //3.Check expected result
        onData(anything()).inAdapterView(withId(R.id.fragment_overview_rv)).atPosition(0).check(matches(isDisplayed()));
    }
    **/

}
