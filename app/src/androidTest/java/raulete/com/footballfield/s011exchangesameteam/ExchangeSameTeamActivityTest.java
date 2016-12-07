package raulete.com.footballfield.s011exchangesameteam;

import android.support.test.espresso.PerformException;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExchangeSameTeamActivityTest {

    @Rule
    public ActivityTestRule<ExchangeSameTeamActivity> mActivityRule = new ActivityTestRule(ExchangeSameTeamActivity.class);

    @Test
    public void letExchangePlayerOfTheSameTeam(){
        onView(withText("Messi")).check(matches(isDisplayed()));
        onView(withText("Neymar")).check(doesNotExist());

        onView(withId(R.id.activity_exchange_neymar)).perform(click());

        onView(withText("Neymar")).check(matches(isDisplayed()));
        onView(withText("Messi")).check(doesNotExist());
    }

    @Test(expected=PerformException.class)
    public void dontLetExchangePlayersOfOtherTeam(){
        onView(withText("Messi")).check(matches(isDisplayed()));
        onView(withText("Ronaldo")).check(doesNotExist());

        onView(withId(R.id.activity_exchange_ronaldo)).perform(click());
    }
}
