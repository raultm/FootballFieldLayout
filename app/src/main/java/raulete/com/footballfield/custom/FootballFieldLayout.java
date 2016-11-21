package raulete.com.footballfield.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import java.util.BitSet;

import raulete.com.footballfield.R;

/**
 * Example of writing a custom layout manager.  This is a fairly full-featured
 * layout manager that is relatively general, handling all layout cases.  You
 * can simplify it for more specific cases.
 *
 * https://developer.android.com/reference/android/view/ViewGroup.html
 *
 * onMeasure
 * All the players are going to have the same dimensions.
 *
 *  ______________________
 * |   D  M    |   M  D   |
 * |   D  M  F | F M  D   |
 * |G          |         G|
 * |   D  M  F | F M  D   |
 * |___D__M____|___M__D___|
 *
 *
 * onLayout
 *
 * The position of a player always will be relative 0-100 from the field point of view to avoid
 * problems when drawing th field in different resolutions
 *
 * 0           50        100
 *  ______________________    0
 * |           |          |
 * |           |          |
 * |           |          |
 * |           |          |
 * |___________|__________|  100
 *
 *
 * The position of the player will be referenced to the middle of the PlayerViewItself. I don't know
 * of it's gonna give problems this decision, but I need to define this now before use different
 * approaches
 *
 *  ___________
 * |           |
 * |           |
 * |     X     |
 * |           |
 * |___________|
 */
@RemoteViews.RemoteView
public class FootballFieldLayout extends RelativeLayout implements FieldPlayerCollection.OnChangesListener{

    private FieldPlayerCollection fieldPlayers = new FieldPlayerCollection();

    public FootballFieldLayout(Context context) {
        super(context);
    }

    public FootballFieldLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FootballFieldLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBackgroundResource(R.mipmap.football_field);
    }

    /**
     * Any layout manager that doesn't scroll will want this.
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }


    /**
     * Ask all children to measure themselves and compute the measurement of this
     * layout based on the children.
     *

     */
    /*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    */

    /** These are used for computing child frames based on their gravity. */
    private final Rect mTmpContainerRect = new Rect();
    private final Rect mTmpChildRect = new Rect();

    /**
     * Position all children within this layout.

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();

        // These are the far left and right edges in which we are performing layout.
        int leftPos = getPaddingLeft();
        int rightPos = right - left - getPaddingRight();

        // These are the top and bottom edges in which we are performing layout.
        final int parentTop = getPaddingTop();
        final int parentBottom = bottom - top - getPaddingBottom();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                final int width = child.getMeasuredWidth();
                final int height = child.getMeasuredHeight();

                mTmpContainerRect.left = leftPos + lp.leftMargin;
                mTmpContainerRect.right = leftPos + width + lp.rightMargin;
                leftPos = mTmpContainerRect.right;
                mTmpContainerRect.top = parentTop + lp.topMargin;
                mTmpContainerRect.bottom = parentBottom - lp.bottomMargin;

                // Use the child's gravity and size to determine its final
                // frame within its container.
                Gravity.apply(Gravity.TOP | Gravity.START, width, height, mTmpContainerRect, mTmpChildRect);

                // Place the child.
                child.layout(mTmpChildRect.left, mTmpChildRect.top,
                        mTmpChildRect.right, mTmpChildRect.bottom);
            }
        }

    }
*/

    // ----------------------------------------------------------------------
    // The rest of the implementation is for custom per-child layout parameters.
    // If you do not need these (for example you are writing a layout manager
    // that does fixed positioning of its children), you can drop all of this.

    /*
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FootballFieldLayout.LayoutParams(getContext(), attrs);
    }
*/


    public FieldPlayerCollection getFieldPlayers() {
        return fieldPlayers;
    }

    public void linkFieldPlayerCollection(FieldPlayerCollection fpc) {
        fpc.addListener(this);
        fieldPlayers = fpc;
        collectionsChanges();

    }

    public void collectionsChanges() {
        removeAllViews();
        for(FieldPlayer fieldPlayer : getFieldPlayers().list)
        {
            Log.i("collctionChanges", fieldPlayer.getShortName());
            addView(new FieldPlayerView(getContext(), fieldPlayer));
        }
    }

    @Override
    public void change(FieldPlayerCollection fpc) {
        collectionsChanges();
    }

    /**
     * Custom per-child layout information.
     */
    public static class LayoutParams extends MarginLayoutParams {
        /**
         * The gravity to apply with the View to which these layout parameters
         * are associated.
         */
        public int gravity = Gravity.TOP | Gravity.START;

        public static int POSITION_LEFT = 1;
        public static int POSITION_RIGHT = 2;

        public int position = POSITION_LEFT;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            // Pull the layout param values from the layout XML during
            // inflation.  This is not needed if you don't care about
            // changing the layout behavior in XML.
            //TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CustomLayoutLP);
            //gravity = a.getInt(R.styleable.CustomLayoutLP_android_layout_gravity, gravity);
            //position = a.getInt(R.styleable.CustomLayoutLP_layout_position, position);
            //a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}