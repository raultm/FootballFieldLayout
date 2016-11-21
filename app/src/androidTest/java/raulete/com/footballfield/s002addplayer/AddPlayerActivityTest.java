package raulete.com.footballfield.s002addplayer;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayerView;
import raulete.com.footballfield.s002addplayer.AddPlayerActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddPlayerActivityTest {

    @Rule
    public ActivityTestRule<AddPlayerActivity> mActivityRule = new ActivityTestRule(AddPlayerActivity.class);

    @Test
    public void byDefaultNoFieldPlayersInField() {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_add_button)).check(matches(isDisplayed()));

        onView(withId(R.id.activity_field)).check(matches(not(hasSibling(withClassName(startsWith("FieldPlayerView"))))));
    }

    @Test
    public void onAddButtonPlayerClickFieldPlayerAddedToLayout() {
        onView(withId(R.id.activity_add_button)).perform(click());

        onView(withId(R.id.activity_field)).check(matches(hasSibling(withClassName(startsWith("FieldPlayerView")))));
    }


}