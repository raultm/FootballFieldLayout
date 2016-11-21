package raulete.com.footballfield;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by raulete on 21/11/16.
 */

public class withBackgroundResource {

    public static Matcher<View> withBackgroundResource(final int resourceId) {
        Checks.checkNotNull(resourceId);
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public boolean matchesSafely(View view) {
                return view.getContext().getResources().getDrawable(R.mipmap.football_field) == view.getBackground();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background resource: ");
            }
        };
    }
}

