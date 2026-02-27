package com.example.android_ui_testing;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    // start from MainActivity
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testActivitySwitched() {

        String testCity = "Edmonton";

        // 1. add a city so we have something to click
        onView(withId(R.id.button_add)).perform(click());
        //type city then close keyboard
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(testCity), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        // 2. find the city we just added in the adapter, scroll to it, and click it.
        onData(allOf(is(instanceOf(String.class)), is(testCity)))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());
        // 3. check if the activity switched
        // if the "back" button is visible on the screen, it proves we successfully changed to ShowActivity
        onView(withId(R.id.button_back)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameIsConsistent() {
        String testCity = "Calgary";
        // 1. add a new city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(testCity), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());


        // 2. click on the city in the ListView to switch screens
        onData(allOf(is(instanceOf(String.class)), is(testCity)))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());

        // 3. verify, the shownCity text view shows our new city name "Calgary"
        onView(withId(R.id.shownCity)).check(matches(withText(testCity)));
        System.out.println(withId(R.id.shownCity));
    }

    @Test
    public void testBackButton() {
        // 1. add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        // 2. click the city to switch to ShowActivity
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // 3. click the back button
        onView(withId(R.id.button_back)).perform(click());

        // 4. verify we returned
        // if we are back on MainActivity, the "ADD CITY" button should be visible again
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}