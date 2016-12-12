package raulete.com.footballfield.s016wholeteam;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s015teamimage.TeamImageActivity;
import raulete.com.footballfieldlayout.FieldPlayerView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.LayoutAssertions.noOverlaps;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.startsWith;
import static raulete.com.footballfield.FFLMatchers.withDrawableResource;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WholeTeamActivityTest {

    @Rule
    public ActivityTestRule<WholeTeamActivity> mActivityRule = new ActivityTestRule(WholeTeamActivity.class);

    @Test
    public void afterAddLocalTeamNoPlayerIsOverlapped() {
        onView(withId(R.id.activity_add_local)).perform(click());

        onView(withId(R.id.activity_field)).check(noOverlaps(withClassName(startsWith("FieldPlayerView"))));
    }

    @Test
    public void afterAddGuestTeamNoPlayerIsOverlapped() {
        onView(withId(R.id.activity_add_guest)).perform(click());

        onView(withId(R.id.activity_field)).check(noOverlaps(withClassName(startsWith("FieldPlayerView"))));
    }

    @Test
    public void afterAddBothTeamsNoPlayerIsOverlapped() {
        onView(withId(R.id.activity_add_local)).perform(click());
        onView(withId(R.id.activity_add_guest)).perform(click());

        onView(withId(R.id.activity_field)).check(noOverlaps(withClassName(startsWith("FieldPlayerView"))));
    }

}