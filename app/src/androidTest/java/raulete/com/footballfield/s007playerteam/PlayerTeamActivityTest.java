package raulete.com.footballfield.s007playerteam;

import android.support.test.espresso.PerformException;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;
import raulete.com.footballfield.s006exchangeplayer.ExchangePlayerActivity;

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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlayerTeamActivityTest {

    @Rule
    public ActivityTestRule<PlayerTeamActivity> mActivityRule = new ActivityTestRule(PlayerTeamActivity.class);

    @Test
    public void afterAddPlayeTeamShieldIsOnTheField(){
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));

        onView(withText("Messi")).check(matches(isDisplayed()));

        onView(withId(R.id.field_left_team_shield)).check(matches(isDisplayed()));
    }

    @Test(expected=PerformException.class)
    public void whenAddingAPlayerFromDifferentTeamOnLocalPositionThrowsException(){
        onView(withText("Messi")).check(matches(isDisplayed()));
        onView(withId(R.id.addKokeLocal)).perform(click());
    }

    @Test
    public void canAddAGuestPlayer(){
        onView(withId(R.id.addKokeGuest)).perform(click());
        onView(withText("Koke")).check(matches(isDisplayed()));
    }

    @Test(expected=PerformException.class)
    public void whenAddingAPlayerFromDifferentTeamOnGuestPositionThrowsException(){
        onView(withId(R.id.addKokeGuest)).perform(click());
        onView(withText("Koke")).check(matches(isDisplayed()));
        onView(withId(R.id.addRonaldoGuest)).perform(click());
    }

}
