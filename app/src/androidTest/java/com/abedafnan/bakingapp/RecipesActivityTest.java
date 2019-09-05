package com.abedafnan.bakingapp;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.abedafnan.bakingapp.adapters.RecipesAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class RecipesActivityTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<RecipesActivity> mTestRule = new ActivityTestRule<>(RecipesActivity.class);


    @Before
    public void registerIdlingResource() {
        mIdlingResource = mTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void checkIfRecyclerViewIsWorking() {
        onView(withId(R.id.recycler_recipes))
                .check(matches(isEnabled()));

        onView(withId(R.id.recycler_recipes))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkRecyclerViewItemText() {
        // First, scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.recycler_recipes))
                .perform(RecyclerViewActions.<RecipesAdapter.RecipesViewHolder>scrollToPosition(1));

        // Match the text in the item mathes what's expected.
        String itemElementText = mTestRule.getActivity().getResources()
                .getString(R.string.item_element_string);
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

}
