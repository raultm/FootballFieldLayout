package raulete.com.footballfield;

import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by raulete on 6/12/16.
 */

public class FFLMatchers {

    public static Matcher<View> withBackgroundColor(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, LinearLayout>(LinearLayout.class) {
            @Override
            public boolean matchesSafely(LinearLayout view) {
                // http://stackoverflow.com/questions/8089054/get-the-background-color-of-a-button-in-android
                return color == ((ColorDrawable)view.getBackground()).getColor();
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("with background color: ");
            }
        };
    }

    public static Matcher<View> withDrawableResource(final int resourceId) {
        Checks.checkNotNull(resourceId);
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public boolean matchesSafely(ImageView view) {
                return view.getContext().getResources().getDrawable(resourceId, null) == view.getDrawable();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background resource: ");
            }
        };
    }
}
