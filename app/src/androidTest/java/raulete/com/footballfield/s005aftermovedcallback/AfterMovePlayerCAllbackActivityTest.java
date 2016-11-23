package raulete.com.footballfield.s005aftermovedcallback;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s004moveonlongclick.MoveOnLongClickActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.longClick;
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
public class AfterMovePlayerCAllbackActivityTest {

    @Rule
    public ActivityTestRule<AfterMovedPlayerCallbackActivity> mActivityRule = new ActivityTestRule(AfterMovedPlayerCallbackActivity.class);

    @Test
    public void afterPlayerIsMovedTextOfPositionInfoChanges() throws InterruptedException {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        TextView tv = (TextView) mActivityRule.getActivity().findViewById(R.id.info_position);

        String beginCoord = tv.getText().toString();
        onView(withId(R.id.player_undefined)).perform(swipeRight());
        onView(withId(R.id.player_undefined)).perform(swipeDown());
        String endCoord = tv.getText().toString();

        assertThat(beginCoord, not(equalTo(endCoord)));
    }

}