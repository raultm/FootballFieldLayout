package raulete.com.footballfield.s001emptyfield;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static raulete.com.footballfield.withBackgroundResource.withBackgroundResource;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BasicFieldActivityTest {

    @Rule
    public ActivityTestRule<BasicFieldActivity> mActivityRule = new ActivityTestRule(BasicFieldActivity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withId(R.id.activity_basic)).check(matches(isDisplayed()));
    }


}