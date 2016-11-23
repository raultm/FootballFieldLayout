package raulete.com.footballfield.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import java.lang.reflect.Field;

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


    public final static int PLAYER_MOVE_ON_LONG_CLICK = 100;
    public final static int PLAYER_MOVE_ON_ADDED = 200;

    public final static int NO_DELTA = -10000;

    private int MOVE_ON_ACTION = PLAYER_MOVE_ON_LONG_CLICK;
    private OnPlayerActionsCallback onPlayerActionsCallback = uselessOnPlayerCallback;


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
        final FieldPlayerView fpv = new FieldPlayerView(getContext(), player);
        addView(fpv);
        activateOnTouchListener(fpv);
    }

    public void setActionToActivateOnTouchListener(int action)
    {
        MOVE_ON_ACTION = action;
    }

    public void activateOnTouchListener(final View view)
    {
        switch (MOVE_ON_ACTION){
            case PLAYER_MOVE_ON_ADDED:
                view.setOnTouchListener(this);
                break;
            case PLAYER_MOVE_ON_LONG_CLICK:default:
                view.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        v.setOnTouchListener(FootballFieldLayout.this);
                        return true;
                    }
                });
                break;
        }
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

    public float dX=NO_DELTA, dY=NO_DELTA;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                setDelta(view, event);
                break;
            case MotionEvent.ACTION_MOVE:
                move(view, event);
                break;
            case MotionEvent.ACTION_UP:
                FieldPlayerView fpv = (FieldPlayerView)view;
                FieldPosition fposition = FieldPosition.createFromEvent(this, event);
                onPlayerActionsCallback.moved(fpv.getFieldPlayer(), fposition);
                view.setOnTouchListener(null);
                activateOnTouchListener(view);
                resetDelta();
                break;
            default:
                return false;
        }
        return true;
    }

    public void setDelta(View view, MotionEvent event)
    {
        dX = view.getX() - event.getRawX();
        dY = view.getY() - event.getRawY();
    }

    public void resetDelta()
    {
        dX = NO_DELTA;
        dY = NO_DELTA;
    }

    public void move(View view, MotionEvent event)
    {
        if(dX == NO_DELTA || dY == NO_DELTA){ setDelta(view, event); }
        view.animate()
                .x(event.getRawX() + dX)
                .y(event.getRawY() + dY)
                .setDuration(0)
                .start();
    }


    public void setOnPlayerActionsCallback(OnPlayerActionsCallback cb) {
        onPlayerActionsCallback = cb;
    }

    public interface OnPlayerActionsCallback{
        void moved(FieldPlayer fp, FieldPosition fieldPosition);
    }

    private final static OnPlayerActionsCallback uselessOnPlayerCallback = new OnPlayerActionsCallback() {
        @Override
        public void moved(FieldPlayer fp, FieldPosition fieldPosition) {

        }
    };
}