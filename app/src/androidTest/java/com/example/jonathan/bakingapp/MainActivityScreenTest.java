package com.example.jonathan.bakingapp;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jonathan.bakingapp.UI.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static  org.hamcrest.core.StringStartsWith.startsWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private IdlingResource mIdlingREsource;

    @Before
    public void registerIdlingResource() {
        mIdlingREsource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingREsource);
    }

    @Test
    public void clickRecyclerView_OpensStepActivity(){
        // Find / Perform / Verify //
        // Find View and Click
        onView(withId(R.id.bakingList_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // Check that activity opens
        onView(withId(R.id.ingredients_tv)).check(matches(withText(startsWith("Ingredient"))));
    }

    @After
    public void unregisterIdlingResource() {
        if(mIdlingREsource != null) {
            Espresso.unregisterIdlingResources(mIdlingREsource);
        }
    }
}
