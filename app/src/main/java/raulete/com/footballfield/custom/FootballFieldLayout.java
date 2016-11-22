package raulete.com.footballfield.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

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
public class FootballFieldLayout extends RelativeLayout implements View.OnTouchListener {

    private FieldPlayerCollection fpc = new FieldPlayerCollection();

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

    public void addPlayer(FieldPlayer player) {
        fpc.add(player);
        FieldPlayerView fpv = new FieldPlayerView(getContext(), player);
        addView(fpv);
        fpv.setOnTouchListener(this);
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

    /**
     * Position all children within this layout.
    */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


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


    // http://stackoverflow.com/questions/9398057/android-move-a-view-on-touch-move-action-move

    public float dX, dY;

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_UP:
                //view.setOnTouchListener(null);
                break;
            default:
                return false;
        }
        return true;
    }



}