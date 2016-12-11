package raulete.com.footballfield.s015teamimage;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static raulete.com.footballfield.FFLMatchers.withDrawableResource;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TeamImageActivityTest {

    @Rule
    public ActivityTestRule<TeamImageActivity> mActivityRule = new ActivityTestRule(TeamImageActivity.class);

    @Ignore
    @Test
    public void onLocalTeamLeftSideHasImage() {
        onView(withId(R.id.activity_add_local)).perform(click());

        onView(withId(R.id.field_left_team_shield)).check(matches(withDrawableResource(R.mipmap.fcb)));
    }

    @Ignore
    @Test
    public void onGuestTeamRightSideHasImage() {
        onView(withId(R.id.activity_add_guest)).perform(click());

        onView(withId(R.id.field_right_team_shield)).check(matches(withDrawableResource(R.mipmap.rmd)));
    }

    @Ignore
    @Test
    public void onChangeFieldImagesChangeToo() {
        onView(withId(R.id.activity_add_local)).perform(click());
        onView(withId(R.id.activity_add_guest)).perform(click());

        // Check Images OK
        onView(withId(R.id.field_left_team_shield)).check(matches(withDrawableResource(R.mipmap.fcb)));
        onView(withId(R.id.field_right_team_shield)).check(matches(withDrawableResource(R.mipmap.rmd)));

        // Change Field
        onView(withId(R.id.activity_change_field_button)).perform(click());

        // Check Images Has Changed
        onView(withId(R.id.field_left_team_shield)).check(matches(withDrawableResource(R.mipmap.rmd)));
        onView(withId(R.id.field_right_team_shield)).check(matches(withDrawableResource(R.mipmap.fcb)));

    }


}