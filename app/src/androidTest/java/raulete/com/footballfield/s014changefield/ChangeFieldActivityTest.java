package raulete.com.footballfield.s014changefield;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s013playerboundaries.PlayerBoundariesActivity;
import raulete.com.footballfieldlayout.FieldCoordinates;
import raulete.com.footballfieldlayout.FieldPlayerView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeFieldActivityTest {

    @Rule
    public ActivityTestRule<ChangeFieldActivity> mActivityRule = new ActivityTestRule(ChangeFieldActivity.class);

    @Test
    public void onChangeFieldViewHasInvertedItsPosition() {
        onView(withId(R.id.activity_add_button)).perform(click());


        FieldPlayerView fpv = (FieldPlayerView) mActivityRule.getActivity().findViewById(R.id.player_undefined);

        FieldCoordinates beginCoord = fpv.getCoords();
        onView(withId(R.id.activity_change_field_button)).perform(click());
        FieldCoordinates endCoord = fpv.getCoords();

        FieldCoordinates endCoordInverted = endCoord.invert();

        assertThat(beginCoord.x(), equalTo(endCoordInverted.x()));
        assertThat(beginCoord.y(), equalTo(endCoordInverted.y()));

    }

    @Test
    public void onChangeFieldAfterSwipingViewHasInvertedItsPosition() {
        onView(withId(R.id.activity_add_button)).perform(click());


        FieldPlayerView fpv = (FieldPlayerView) mActivityRule.getActivity().findViewById(R.id.player_undefined);

        onView(withId(R.id.player_undefined)).perform(swipeRight(), swipeUp());

        FieldCoordinates beginCoord = fpv.getCoords();
        onView(withId(R.id.activity_change_field_button)).perform(click());
        FieldCoordinates endCoord = fpv.getCoords();

        FieldCoordinates endCoordInverted = endCoord.invert();

        assertThat(beginCoord.x(), equalTo(endCoordInverted.x()));
        assertThat(beginCoord.y(), equalTo(endCoordInverted.y()));

    }
}