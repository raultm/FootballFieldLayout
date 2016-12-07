package raulete.com.footballfield.s004moveonlongclick;

import android.app.UiAutomation;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.MotionEvent;
import android.view.View;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raulete.com.footballfield.R;

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
public class MoveOnLongClickActivityTest {

    @Rule
    public ActivityTestRule<MoveOnLongClickActivity> mActivityRule = new ActivityTestRule(MoveOnLongClickActivity.class);

    @Test
    public void byDefaultPlayerOnField() {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        onView(withId(R.id.player_undefined)).check(matches(isDisplayed()));
    }

    // http://stackoverflow.com/questions/34683956/how-to-get-a-view-from-within-espresso-to-pass-into-an-idlingresource
    @Test
    public void playerCantMovedVerticallyAndHorizontallyByDefault() throws InterruptedException {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        View view = mActivityRule.getActivity().findViewById(R.id.player_undefined);

        float[] beginCoord = {view.getX(), view.getY()};
        onView(withId(R.id.player_undefined)).perform(swipeDown());
        onView(withId(R.id.player_undefined)).perform(swipeRight());
        float[] endCoord = {view.getX(), view.getY()};

        assertThat(beginCoord[0], equalTo(endCoord[0]));
        assertThat(beginCoord[1], equalTo(endCoord[1]));
    }

    @Ignore("Working on http://bit.ly/2gMsfzU")
    @Test
    public void playerCanMovedVerticallyAndHorizontallyAfterLongClick() throws InterruptedException {
        onView(withId(R.id.activity_field)).check(matches(isDisplayed()));
        View view = mActivityRule.getActivity().findViewById(R.id.player_undefined);

        float[] beginCoord = {view.getX(), view.getY()};
        onView(withId(R.id.player_undefined)).perform(longClick());
        drag(InstrumentationRegistry.getInstrumentation().getUiAutomation(),0, 100, 0, 100, 20);
        float[] endCoord = {view.getX(), view.getY()};

        assertThat(beginCoord[0], not(equalTo(endCoord[0])));
        assertThat(beginCoord[1], not(equalTo(endCoord[1])));
    }


    //public static void drag(Instrumentation inst, float fromX, float toX, float fromY,
    public static void drag(UiAutomation uiautomation, float fromX, float toX, float fromY,
                            float toY, int stepCount) throws InterruptedException {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();

        float y = fromY;
        float x = fromX;

        float yStep = (toY - fromY) / stepCount;
        float xStep = (toX - fromX) / stepCount;

        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, 0);
        uiautomation.injectInputEvent(event, true);
        //Thread.sleep(5000);
        for (int i = 0; i < stepCount; ++i) {
            y += yStep;
            x += xStep;
            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
            uiautomation.injectInputEvent(event, true);
        }

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);
        uiautomation.injectInputEvent(event, true);
        //uiautomation.waitForIdleSync();
    }

}