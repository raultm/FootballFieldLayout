package raulete.com.footballfield.s013playerboundaries;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s012adplayercustomposition.CustomPositionActivity;
import raulete.com.footballfieldlayout.FieldCoordinates;
import raulete.com.footballfieldlayout.FieldPlayerView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlayerBoundariesActivityTest {

    @Rule
    public ActivityTestRule<PlayerBoundariesActivity> mActivityRule = new ActivityTestRule(PlayerBoundariesActivity.class);

    @Test
    public void onBoundariesNonePlayerLeaveTheFieldFromTheLeft() {
        onView(withId(R.id.addLocalPlayer)).perform(click());
        onView(withId(R.id.setBoundariesNone)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeLeft())
                .check(matches(not(isCompletelyDisplayed())));

    }

    @Test
    public void onBoundariesFieldPlayerCantLeaveTheFieldFromTheLeft() {
        onView(withId(R.id.addLocalPlayer)).perform(click());
        onView(withId(R.id.setBoundariesField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeLeft())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesHalfFieldPlayerCantLeaveTheFieldFromTheLeft() {
        onView(withId(R.id.addLocalPlayer)).perform(click());
        onView(withId(R.id.setBoundariesHalfField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeLeft())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesNonePlayerLeaveTheFieldFromTheTop() {
        onView(withId(R.id.addLocalPlayer)).perform(click());
        onView(withId(R.id.setBoundariesNone)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeUp())
                .check(matches(not(isCompletelyDisplayed())));

    }

    @Test
    public void onBoundariesFieldPlayerCantLeaveTheFieldFromTheTop() {
        onView(withId(R.id.addLocalPlayer)).perform(click());
        onView(withId(R.id.setBoundariesField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeUp())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesHalfFieldPlayerCantLeaveTheFieldFromTheTop() {
        onView(withId(R.id.addLocalPlayer)).perform(click());
        onView(withId(R.id.setBoundariesHalfField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeUp())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesNonePlayerLeaveTheFieldFromTheBottom() {
        onView(withId(R.id.addGuestPlayer)).perform(click());
        onView(withId(R.id.setBoundariesNone)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeDown())
                .check(matches(not(isCompletelyDisplayed())));

    }

    @Test
    public void onBoundariesFieldPlayerCantLeaveTheFieldFromTheBottom() {
        onView(withId(R.id.addGuestPlayer)).perform(click());
        onView(withId(R.id.setBoundariesField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeDown())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesHalfFieldPlayerCantLeaveTheFieldFromTheBottom() {
        onView(withId(R.id.addGuestPlayer)).perform(click());
        onView(withId(R.id.setBoundariesHalfField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeDown())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesNonePlayerLeaveTheFieldFromTheRight() {
        onView(withId(R.id.addGuestPlayer)).perform(click());
        onView(withId(R.id.setBoundariesNone)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeDown())
                .check(matches(not(isCompletelyDisplayed())));

    }

    @Test
    public void onBoundariesFieldPlayerCantLeaveTheFieldFromTheRight() {
        onView(withId(R.id.addGuestPlayer)).perform(click());
        onView(withId(R.id.setBoundariesField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeDown())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesHalfFieldPlayerCantLeaveTheFieldFromTheRight() {
        onView(withId(R.id.addGuestPlayer)).perform(click());
        onView(withId(R.id.setBoundariesHalfField)).perform(click());

        onView(withId(R.id.player_undefined))
                .perform(swipeDown())
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void onBoundariesHalfFieldLocalPlayerCantInvadeGuestHalfField() {
        onView(withId(R.id.setBoundariesHalfField)).perform(click());
        onView(withId(R.id.addLocalPlayerMidfield)).perform(click());

        FieldPlayerView fpv = (FieldPlayerView)mActivityRule.getActivity().findViewById(R.id.player_undefined);

        FieldCoordinates beginCoord = fpv.getCoords();
        onView(withId(R.id.player_undefined)).perform(swipeRight());
        FieldCoordinates endCoord = fpv.getCoords();

        assertThat(beginCoord.x(), equalTo(endCoord.x()));
        assertThat(beginCoord.y(), equalTo(endCoord.y()));

    }

    @Test
    public void onBoundariesHalfFieldGuestPlayerCantInvadeLocalHalfField() {
        onView(withId(R.id.setBoundariesHalfField)).perform(click());
        onView(withId(R.id.addGuestPlayerMidfield)).perform(click());

        View view = mActivityRule.getActivity().findViewById(R.id.player_undefined);

        float[] beginCoord = {view.getX(), view.getY()};
        onView(withId(R.id.player_undefined)).perform(swipeLeft());
        float[] endCoord = {view.getX(), view.getY()};

        assertThat(beginCoord[0], equalTo(endCoord[0]));
        assertThat(beginCoord[1], equalTo(endCoord[1]));

    }
}