package raulete.com.footballfieldlayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

import static raulete.com.footballfieldlayout.FFL.coords;

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
 * ______________________
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
 * ______________________    0
 * |           |          |
 * |           |          |
 * |           |          |
 * |           |          |
 * |___________|__________|  100
 *
 *
 * The position of the player will be referenced to the middle of the PlayerViewItself. I don't know
 * of it's gonna give problems this decision, but I need to define this now before use different
 * approaches.
 *
 * If I use this approach a lot of problems to positionate, but otherwise I'll have problems when
 * strugglign agains diferent screens. The width/hight of the view doesn't need to modify the position
 * of the player over the field.
 *
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

    private final static int NO_DELTA = Integer.MIN_VALUE;

    private boolean vibrationFeedback = true;

    private int BOUNDARIES_TYPE = FieldBoundaries.BOUNDARIES_FIELD;
    private int MOVE_ON_ACTION = PLAYER_MOVE_ON_LONG_CLICK;
    private OnPlayerActionsCallback onPlayerActionsCallback = uselessOnPlayerCallback;
    private OnPlayerClickCallback onPlayerClickCallback = uselessOnPlayerClickCallback;
    private FieldImageLoader imageLoader = uselessFieldImageLoader;

    private FieldPlayerCollection fpc = new FieldPlayerCollection();

    private final static int LOCAL_TEAM = 1;
    private final static int GUEST_TEAM = 2;

    private FieldTeam localTeam;
    private FieldTeam guestTeam;

    private int leftSideTeam = LOCAL_TEAM;

    // http://stackoverflow.com/questions/9398057/android-move-a-view-on-touch-move-action-move
    public float dX = NO_DELTA, dY = NO_DELTA;

    public void changeField() {
        if (leftSideTeam == LOCAL_TEAM) {
            leftSideTeam = GUEST_TEAM;
        } else {
            leftSideTeam = LOCAL_TEAM;
        }

        fpc.invertPositions();

        FieldTeamView lftv = (FieldTeamView) findViewById(R.id.field_left_team_shield);
        FieldTeamView rftv = (FieldTeamView) findViewById(R.id.field_right_team_shield);

        Drawable temp = lftv.getDrawable();
        lftv.setImageDrawable(rftv.getDrawable());
        rftv.setImageDrawable(temp);

    }

    /*
     * Constructors
     */
    public FootballFieldLayout(Context context) {
        super(context);
    }

    public FootballFieldLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FootballFieldLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBackgroundResource(R.mipmap.football_field);
        addView(new FieldTeamView(getContext(), R.id.field_left_team_shield, coords(25f, 50f)));
        addView(new FieldTeamView(getContext(), R.id.field_right_team_shield, coords(25f, 50f).inverse()));
    }

    /*
     * Add Players
     */
    public void addPlayerLocal(FieldPlayer player) {
        addLocal(player);
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

    private void addPlayer(int teamType, FieldPlayer player, FieldCoordinates fieldCoordinates) throws IllegalArgumentException {
        // TODO Refactoring duplicate behaviout on teamType
        if (teamType == LOCAL_TEAM) {
            if (localTeam == null) {
                localTeam = player.getTeam();
            } else if (localTeam != player.getTeam()) {
                throw new IllegalArgumentException();
            } else {

            }
            if (fieldCoordinates == null) {
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
            if (fieldCoordinates == null) {
                fieldCoordinates = FieldCoordinates.create();
            }
        }

        FieldPlayerView fpv = new FieldPlayerView(getContext(), player, fieldCoordinates);
        addPlayerView(fpv);
    }

    public void addPlayerView(final FieldPlayerView fpv) {
        int side = R.id.field_left_team_shield;
        if(isRightSide(fpv)){
            side = R.id.field_right_team_shield;
        }
        FieldTeamView fieldTeamView = (FieldTeamView) findViewById(side);
        if(fieldTeamView.getDrawable() == null){
            imageLoader.load(fieldTeamView, fpv.getFieldPlayer().getTeam().getShortName());
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
                final FieldPlayerView fpv = (FieldPlayerView) view;
                view.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        vibration();
                        v.setOnTouchListener(FootballFieldLayout.this);
                        return true;
                    }
                });
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onPlayerClickCallback.click(fpv);
                        Log.i("Click", fpv.getFieldPlayer().getName());
                    }
                });
                break;
        }
    }

    private void vibration() {
        if (vibrationFeedback)
            ((Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);
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

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        FieldPlayerView fpv = (FieldPlayerView) view;
        FieldPosition fposition = FieldPosition.createFromEvent(this, event);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                setDelta(fpv, event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (onPlayerActionsCallback.moving(fpv, fposition)) {
                    move(fpv, event);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (onPlayerActionsCallback.moving(fpv, fposition)) {
                    fposition = move(fpv, event);
                }
                FieldPosition fieldPositionPlayer = FieldPosition.createFromRawXY(this, fposition.getXinPx() + fpv.getWidthDelta(), fposition.getYinPx() + fpv.getHeightDelta());
                onPlayerActionsCallback.moved(fpv, fieldPositionPlayer);
                fpv.setCoords(fpv.getCoords());
                view.setOnTouchListener(null);
                activateOnTouchListener(view);
                resetDelta();
                break;
            default:
                return false;
        }
        return true;
    }

    public FieldPosition rectifyPosition(FieldPlayerView fpv, FieldPosition fposition) {

        float x = fposition.getXinPx();
        float y = fposition.getYinPx();

        FieldBoundaries fb = FieldBoundaries.getBoundaries(BOUNDARIES_TYPE, this, fpv);

        if (x < fb.getxMin()) {
            x = fb.getxMin();
        }
        if (y < fb.getyMin()) {
            y = fb.getyMin();
        }

        if (x > fb.getxMax()) {
            x = fb.getxMax();
        }
        if (y > fb.getyMax()) {
            y = fb.getyMax();
        }

        return FieldPosition.createFromRawXY(this, x - fpv.getWidthDelta(), y - fpv.getHeightDelta());
    }

    private void setDelta(FieldPlayerView fpv, MotionEvent event) {
        dX = fpv.getX() + fpv.getWidthDelta() - event.getRawX();
        dY = fpv.getY() + fpv.getHeightDelta() - event.getRawY();
    }

    private void resetDelta() {
        dX = NO_DELTA;
        dY = NO_DELTA;
    }

    private FieldPosition move(FieldPlayerView view, MotionEvent event) {
        if (dX == NO_DELTA || dY == NO_DELTA) {
            setDelta(view, event);
        }
        return move(view, event.getRawX() + dX, event.getRawY() + dY);
    }

    public FieldPosition move(FieldPlayerView view, float x, float y) {
        return move(view, x, y, 0);
    }

    public FieldPosition move(FieldPlayerView fpv, float x, float y, int duration) {
        FieldPosition fposition = FieldPosition.createFromRawXY(this, x, y);
        fposition = rectifyPosition(fpv, fposition);

        //fpv.setCoords(fposition.getCoords());
        // http://stackoverflow.com/questions/30859572/how-to-reset-view-to-original-state-after-using-animators-to-animates-its-some-p
        fpv.animate()
                .x(fposition.getXinPx())
                .y(fposition.getYinPx())
                .setDuration(duration)
                .start();

        return fposition;
    }


    public void setOnPlayerActionsCallback(OnPlayerActionsCallback cb) {
        onPlayerActionsCallback = cb;
    }

    public void exchange(FieldPlayer playerFromFieldToBench, FieldPlayer playerFromBenchToField) {
        if (!playerFromBenchToField.getTeam().equals(playerFromFieldToBench.getTeam())) {
            throw new IllegalArgumentException();
        }
        fpc.exchange(playerFromFieldToBench, playerFromBenchToField);
    }

    public void setOnClickPlayerListener(OnPlayerClickCallback cb) {
        onPlayerClickCallback = cb;
    }

    public void setBoundariesNone() {
        BOUNDARIES_TYPE = FieldBoundaries.BOUNDARIES_NONE;
    }

    public void setBoundariesField() {
        BOUNDARIES_TYPE = FieldBoundaries.BOUNDARIES_FIELD;
    }

    public void setBoundariesHalfField() {
        BOUNDARIES_TYPE = FieldBoundaries.BOUNDARIES_HALF_FIELD;
    }

    public boolean isLocal(FieldPlayer player) {
        return player.getTeam().equals(localTeam);
    }

    public boolean isGuest(FieldPlayer player) {
        return player.getTeam().equals(guestTeam);
    }

    public boolean isLeftSide(FieldPlayerView fpv) {
        if(fpv.getFieldPlayer().getTeam().equals(localTeam) && leftSideTeam == LOCAL_TEAM){ return true; }
        if(fpv.getFieldPlayer().getTeam().equals(guestTeam) && leftSideTeam == GUEST_TEAM){ return true; }
        return false;
    }

    public boolean isRightSide(FieldPlayerView fpv) {
        if(fpv.getFieldPlayer().getTeam().equals(localTeam) && leftSideTeam != LOCAL_TEAM){ return true; }
        if(fpv.getFieldPlayer().getTeam().equals(guestTeam) && leftSideTeam != GUEST_TEAM){ return true; }
        return false;
    }

    public void setImageLoader(FieldImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    // VARARGS
    public void addLocal(FieldPlayer... players) {
        FieldCoordinates[] coordinates = getCoordinates(players);

        for(int i = 0; i < players.length; i++){
            addPlayerLocal(players[i], coordinates[i]);
        }
    }

    public void addGuest(FieldPlayer... players) {
        FieldCoordinates[] coordinates = getCoordinates(players);

        for(int i = 0; i < players.length; i++){
            addPlayerGuest(players[i], coordinates[i].inverse());
            //addPlayerGuest(players[i]);
        }
    }

    // TODO Basic implementation
    private FieldCoordinates[] getCoordinates(FieldPlayer[] players){
        FieldCoordinates[] coordinates = new FieldCoordinates[players.length];

        int g = 0; // goalkeepers
        int d = 0; // defenses
        int m = 0; // midfielders
        int f = 0; // forwards

        for(int i = 0; i < players.length; i++){
            if(players[i].isGoalkeeper())   { g++; coordinates[i] = coords( 5,  0);}
            if(players[i].isDefense())      { d++; coordinates[i] = coords(17,  0);}
            if(players[i].isMidfielder())   { m++; coordinates[i] = coords(30,  0);}
            if(players[i].isForward())      { f++; coordinates[i] = coords(42,  0);}
        }

        int dg = 100 / (g+1); // delta goalkeeper
        int dd = 100 / (d+1); // delta defenses
        int dm = 100 / (m+1); // delta midfielders
        int df = 100 / (f+1); // delta forwards

        int lg = 0; // last goalkeeper
        int ld = 0; // last defenses
        int lm = 0; // last midfielders
        int lf = 0; // last forwards

        for(int i = 0; i < players.length; i++){
            if(players[i].isGoalkeeper())   { coordinates[i] = coords( 5,  lg + dg); lg+=dg; }
            if(players[i].isDefense())      { coordinates[i] = coords(17,  ld + dd); ld+=dd; }
            if(players[i].isMidfielder())   { coordinates[i] = coords(30,  lm + dm); lm+=dm; }
            if(players[i].isForward())      { coordinates[i] = coords(42,  lf + df); lf+=df; }
        }

        return coordinates;
    }

    public void remove(FieldPlayer player) {
        if(fpc.teammatesCount(player) == 0) {
            // TODO Test to add player with same role(local/guest) but different team check is OK
            if(isLocal(player)){
                localTeam = null;
            }

            if(isGuest(player)){
                guestTeam = null;
            }
            // http://stackoverflow.com/questions/2859212/how-to-clear-an-imageview-in-android
            int side = R.id.field_left_team_shield;
            if (isRightSide(fpc.get(player))) {
                side = R.id.field_right_team_shield;
            }

            FieldTeamView fieldTeamView = (FieldTeamView) findViewById(side);
            if (fieldTeamView.getDrawable() != null) {
                fieldTeamView.setImageDrawable(null);
            }
        }
        fpc.remove(player);
    }

    public interface OnPlayerActionsCallback {
        boolean moving(FieldPlayerView fPlayer, FieldPosition fPosition);

        void moved(FieldPlayerView fPlayer, FieldPosition fPosition);
    }

    public interface OnPlayerClickCallback {
        void click(FieldPlayerView fPlayer);
    }

    private final static OnPlayerActionsCallback uselessOnPlayerCallback = new OnPlayerActionsCallback() {
        @Override
        public boolean moving(FieldPlayerView fPlayer, FieldPosition fPosition) {
            return true;
        }

        @Override
        public void moved(FieldPlayerView fp, FieldPosition fieldPosition) {

        }
    };

    private final static OnPlayerClickCallback uselessOnPlayerClickCallback = new OnPlayerClickCallback() {

        @Override
        public void click(FieldPlayerView fPlayer) {

        }
    };

    private final static FieldImageLoader uselessFieldImageLoader = new FieldImageLoader() {
        @Override
        public void load(FieldTeamView fieldTeamView, String url) {

        }
    };
}