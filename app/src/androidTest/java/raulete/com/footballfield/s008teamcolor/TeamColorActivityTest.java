package raulete.com.footballfield.s008teamcolor;

import android.graphics.Color;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static raulete.com.footballfield.FFLMatchers.withBackgroundColor;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TeamColorActivityTest {

    @Rule
    public ActivityTestRule<TeamColorActivity> mActivityRule = new ActivityTestRule(TeamColorActivity.class);

    @Test
    public void afterAddMessiCheckBackgroundColorIsFCBColor(){

        onView(withId(R.id.addMessiLocal)).perform(click());

        onView(withText("Messi")).check(matches(isDisplayed()));

        onView(withId(R.id.player_undefined)).check(matches(withBackgroundColor(Color.parseColor("#3333FF"))));

    }

    @Test
    public void afterAddRonaldoCheckBackgroundColorIsRMDColor(){

        onView(withId(R.id.addRonaldoGuest)).perform(click());

        onView(withText("Ronaldo")).check(matches(isDisplayed()));

        onView(withId(R.id.player_undefined)).check(matches(withBackgroundColor(Color.parseColor("#FFFFFF"))));

    }

}
