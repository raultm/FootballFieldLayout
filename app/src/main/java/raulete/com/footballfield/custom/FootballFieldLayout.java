package raulete.com.footballfield.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import raulete.com.footballfield.R;

/**
 * Example of writing a custom layout manager.  This is a fairly full-featured
 * layout manager that is relatively general, handling all layout cases.  You
 * can simplify it for more specific cases.
 * <p>
 * https://developer.android.com/reference/android/view/ViewGroup.html
 * <p>
 * onMeasure
 * All the players are going to have the same dimensions.
 * <p>
 * ______________________
 * |   D  M    |   M  D   |
 * |   D  M  F | F M  D   |
 * |G          |         G|
 * |   D  M  F | F M  D   |
 * |___D__M____|___M__D___|
 * <p>
 * <p>
 * onLayout
 * <p>
 * The position of a player always will be relative 0-100 from the field point of view to avoid
 * problems when drawing th field in different resolutions
 * <p>
 * 0           50        100
 * ______________________    0
 * |           |          |
 * |           |          |
 * |           |          |
 * |           |          |
 * |___________|__________|  100
 * <p>
 * <p>
 * The position of the player will be referenced to the middle of the PlayerViewItself. I don't know
 * of it's gonna give problems this decision, but I need to define this now before use different
 * approaches
 * <p>
 * ___________
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


    private List<FieldTeamView> teams = new ArrayList<>();


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

    private final static int LOCAL_TEAM = 1;
    private final static int GUEST_TEAM = 2;

    private FieldTeam localTeam;
    private FieldTeam guestTeam;

    private int teamOnLeft = LOCAL_TEAM;

    public void addPlayerLocal(FieldPlayer player) {
        addPlayer(LOCAL_TEAM, player);
    }

    public void addPlayerLocal(FieldPlayer player, FieldCoordinates fieldCoordinates) {
        addPlayer(LOCAL_TEAM, player, fieldCoordinates);
    }

    public void addPlayerGuest(FieldPlayer player) {
        addPlayer(GUEST_TEAM, player);
    }

    public void addPlayerGuest(FieldPlayer player, FieldCoordinates fieldCoordinates) {
        addPlayer(GUEST_TEAM, player, fieldCoordinates);
    }

    private void addPlayer(int teamType, FieldPlayer player) {
        addPlayer(teamType, player, null);
    }

    private void addPlayer(int teamType, FieldPlayer player, FieldCoordinates fieldCoordinates) throws IllegalArgumentException{
        if (teamType == LOCAL_TEAM) {
            if (localTeam == null) {
                localTeam = player.getTeam();
            } else if (localTeam != player.getTeam()) {
                throw new IllegalArgumentException();
            } else {

            }
            if(fieldCoordinates == null){
                fieldCoordinates = FieldCoordinates.create();
            }
        }

        if (teamType == GUEST_TEAM) {
            if (guestTeam == null) {
                guestTeam = player.getTeam();
            } else if (guestTeam != player.getTeam()) {
                throw new IllegalArgumentException();
            } else {

            }
            if(fieldCoordinates == null){
                fieldCoordinates = FieldCoordinates.create();
            }
        }

        FieldPlayerView fpv = new FieldPlayerView(getContext(), player, fieldCoordinates);
        addPlayerView(fpv);
    }

    public void addPlayerView(FieldPlayerView fpv) {
        if (teams.size() == 0) {
            FieldTeamView ftv = new FieldTeamView(this.getContext(), R.id.field_left_team_shield, fpv.getFieldPlayer().getTeam(), FieldCoordinates.create(25f, 50f));
            teams.add(ftv);
            addView(ftv, 0);
        }
        fpc.add(fpv);
        addView(fpv);
        activateOnTouchListener(fpv);
    }

    public void setActionToActivateOnTouchListener(int action) {
        MOVE_ON_ACTION = action;
    }

    public void activateOnTouchListener(final View view) {
        switch (MOVE_ON_ACTION) {
            case PLAYER_MOVE_ON_ADDED:
                view.setOnTouchListener(this);
                break;
            case PLAYER_MOVE_ON_LONG_CLICK:
            default:
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
     * Position all children within this layout.
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    // http://stackoverflow.com/questions/9398057/android-move-a-view-on-touch-move-action-move
    public float dX = NO_DELTA, dY = NO_DELTA;

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
                FieldPlayerView fpv = (FieldPlayerView) view;
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

    public void setDelta(View view, MotionEvent event) {
        dX = view.getX() - event.getRawX();
        dY = view.getY() - event.getRawY();
    }

    public void resetDelta() {
        dX = NO_DELTA;
        dY = NO_DELTA;
    }

    public void move(View view, MotionEvent event) {
        if (dX == NO_DELTA || dY == NO_DELTA) {
            setDelta(view, event);
        }
        view.animate()
                .x(event.getRawX() + dX)
                .y(event.getRawY() + dY)
                .setDuration(0)
                .start();
    }


    public void setOnPlayerActionsCallback(OnPlayerActionsCallback cb) {
        onPlayerActionsCallback = cb;
    }

    public void exchange(FieldPlayer playerFromFieldToBench, FieldPlayer playerFromBenchToField) {
        fpc.exchange(playerFromFieldToBench, playerFromBenchToField);
    }

    public interface OnPlayerActionsCallback {
        void moved(FieldPlayer fp, FieldPosition fieldPosition);
    }

    private final static OnPlayerActionsCallback uselessOnPlayerCallback = new OnPlayerActionsCallback() {
        @Override
        public void moved(FieldPlayer fp, FieldPosition fieldPosition) {

        }
    };
}