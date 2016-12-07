package raulete.com.footballfield.s003moveplayer;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovePlayerActivityTest {

    @Rule
    public ActivityTestRule<MovePlayerActivity> mActivityRule = new ActivityTestRule(MovePlayerActivity.class);

    @Test
    public void byDefaultPlayerOnField() {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        onView(withId(R.id.player_undefined)).check(matches(isDisplayed()));
    }

    // http://stackoverflow.com/questions/34683956/how-to-get-a-view-from-within-espresso-to-pass-into-an-idlingresource
    @Test
    public void playerCanMovedVerticallyAndHorizontally() throws InterruptedException {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        View view = mActivityRule.getActivity().findViewById(R.id.player_undefined);

        float[] beginCoord = {view.getX(), view.getY()};
        onView(withId(R.id.player_undefined)).perform(swipeDown());
        onView(withId(R.id.player_undefined)).perform(swipeRight());
        float[] endCoord = {view.getX(), view.getY()};

        assertThat("X", beginCoord[0], not(equalTo(endCoord[0])));
        assertThat("Y", beginCoord[1], not(equalTo(endCoord[1])));
    }




}