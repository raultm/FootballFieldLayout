package raulete.com.footballfield.s010clickplayer;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldPlayerView;
import raulete.com.footballfieldlayout.FootballFieldLayout;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ClickPlayerActivityTest {

    @Rule
    public ActivityTestRule<ClickPlayerActivity> mActivityRule = new ActivityTestRule(ClickPlayerActivity.class);

    @Test
    public void callbackIsCalled()  {
        FootballFieldLayout ffl = (FootballFieldLayout) mActivityRule.getActivity().findViewById(R.id.activity_field);
        final FootballFieldLayout.OnPlayerClickCallback callBack =
                mock(FootballFieldLayout.OnPlayerClickCallback.class);

        ffl.setOnClickPlayerListener(callBack);

        onView(withId(R.id.player_undefined)).perform(click());
        //onView(withId(R.id.player_undefined)).perform(swipeDown());

        verify(callBack, atLeast(1)).click(any(FieldPlayerView.class));

    }
}