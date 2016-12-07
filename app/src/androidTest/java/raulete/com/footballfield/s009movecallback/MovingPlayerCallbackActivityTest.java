package raulete.com.footballfield.s009movecallback;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldPosition;
import raulete.com.footballfield.custom.FootballFieldLayout;
import raulete.com.footballfield.s005aftermovedcallback.AfterMovedPlayerCallbackActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovingPlayerCallbackActivityTest {

    @Rule
    public ActivityTestRule<MovingCallbackActivity> mActivityRule = new ActivityTestRule(MovingCallbackActivity.class);

    @Test
    public void afterPlayerIsMovedTextOfPositionInfoChanges() throws InterruptedException {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        TextView tv = (TextView) mActivityRule.getActivity().findViewById(R.id.current_position);

        String beginCoord = tv.getText().toString();
        onView(withId(R.id.player_undefined)).perform(swipeRight());
        onView(withId(R.id.player_undefined)).perform(swipeDown());
        String endCoord = tv.getText().toString();

        assertThat(beginCoord, not(equalTo(endCoord)));
    }

    @Test
    public void callbackIsCalled()  {
        FootballFieldLayout ffl = (FootballFieldLayout) mActivityRule.getActivity().findViewById(R.id.activity_field);
        // http://stackoverflow.com/questions/27348625/java-unit-testing-the-easiest-way-to-test-if-a-callback-is-invoked
        final FootballFieldLayout.OnPlayerActionsCallback callBack =
                mock(FootballFieldLayout.OnPlayerActionsCallback.class);

        ffl.setOnPlayerActionsCallback(callBack);

        onView(withId(R.id.player_undefined)).perform(swipeRight());
        //onView(withId(R.id.player_undefined)).perform(swipeDown());

        verify(callBack, atLeast(4)).moving(any(FieldPlayer.class), any(FieldPosition.class));

    }
}