package raulete.com.footballfield.s006exchangeplayer;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s005aftermovedcallback.AfterMovedPlayerCallbackActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExchangePlayerActivityTest {

    @Rule
    public ActivityTestRule<ExchangePlayerActivity> mActivityRule = new ActivityTestRule(ExchangePlayerActivity.class);

    @Test
    public void afterExchangeMessiOutOfFieldAndNeymarOnField() throws InterruptedException {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));

        onView(withText("Messi")).check(matches(isDisplayed()));
        onView(withText("Neymar")).check(doesNotExist());

        onView(withId(R.id.activity_exchange_button)).perform(click());

        onView(withText("Neymar")).check(matches(isDisplayed()));
        onView(withText("Messi")).check(doesNotExist());
    }

    @Test
    public void afterMoveMessiIfExchangeNeymarHasTheSamePosition() {

        View view = mActivityRule.getActivity().findViewById(R.id.player_undefined);
        float[] beginCoord = {view.getX(), view.getY()};
        onView(withId(R.id.player_undefined)).perform(swipeDown());
        onView(withId(R.id.player_undefined)).perform(swipeRight());
        float[] endCoord = {view.getX(), view.getY()};


        assertThat(beginCoord[0], not(equalTo(endCoord[0])));
        assertThat(beginCoord[1], not(equalTo(endCoord[1])));

        onView(withId(R.id.activity_exchange_button)).perform(click());

        View neymarView = mActivityRule.getActivity().findViewById(R.id.player_undefined);
        float[] neymarCoord = {neymarView.getX(), neymarView.getY()};

        assertThat(neymarCoord[0], equalTo(endCoord[0]));
        assertThat(neymarCoord[1], equalTo(endCoord[1]));
    }
}
