package raulete.com.footballfield.s012customposition;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s012adplayercustomposition.CustomPositionActivity;
import raulete.com.footballfieldlayout.FieldPlayerView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CustomPositionActivityTest {

    @Rule
    public ActivityTestRule<CustomPositionActivity> mActivityRule = new ActivityTestRule(CustomPositionActivity.class);

    @Test
    public void onAddButtonPlayerClickFieldPlayerAddedToLayout() {
        onView(withId(R.id.player_undefined)).check(doesNotExist());

        onView(withId(R.id.activity_add_button)).perform(click());

        onView(withId(R.id.player_undefined)).check(matches(isDisplayed()));
        View field = mActivityRule.getActivity().findViewById(R.id.activity_field);
        FieldPlayerView playerView = (FieldPlayerView) mActivityRule.getActivity().findViewById(R.id.player_undefined);


        float[] coord = playerView.getCoords();

        assertThat(coord[0] / field.getWidth() * 100, equalTo(50f));
        assertThat(coord[1] / field.getHeight() * 100, equalTo(50f));

    }
}