package raulete.com.footballfield.s017removeplayer;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s016wholeteam.WholeTeamActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.LayoutAssertions.noOverlaps;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.startsWith;
import static raulete.com.footballfield.FFLMatchers.noDrawable;
import static raulete.com.footballfield.FFLMatchers.withDrawable;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RemovePlayerActivityTest {

    @Rule
    public ActivityTestRule<RemovePlayerActivity> mActivityRule = new ActivityTestRule(RemovePlayerActivity.class);

    @Test
    public void afterRemoveMessiNoPlayerNoShield() {
        onView(withId(R.id.activity_remove_messi)).perform(click());

        onView(withId(R.id.field_left_team_shield)).check(matches(noDrawable()));
    }

    @Test
    public void ifTwoPlayersAndRemoveOneShieldStillPresent() {
        onView(withId(R.id.activity_add_messi_neymar)).perform(click());
        onView(withId(R.id.activity_remove_messi)).perform(click());

        onView(withId(R.id.field_left_team_shield)).check(matches(withDrawable(R.mipmap.fcb)));
    }

    @Test
    public void ifTwoPlayerAndRemoveBothNoShield() {
        onView(withId(R.id.activity_add_messi_neymar)).perform(click());
        onView(withId(R.id.activity_remove_messi)).perform(click());
        onView(withId(R.id.activity_remove_neymar)).perform(click());

        onView(withId(R.id.field_left_team_shield)).check(matches(noDrawable()));
    }

}