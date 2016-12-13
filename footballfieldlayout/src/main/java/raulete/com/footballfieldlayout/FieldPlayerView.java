package raulete.com.footballfieldlayout;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import static raulete.com.footballfieldlayout.FFL.position;

/**
 * Created by raulete on 16/11/16.
 */

public class FieldPlayerView extends LinearLayout {

    private FieldPlayer fp;
    private FieldCoordinates fc;

    private int totalWeight = 10;
    private int numberWeight = 7; // 70% for the number

    // Settled onMeasure()
    private int width;
    private int height;

    private int numberTextColor = Color.parseColor("#FFFFFF");

    public FieldPlayerView(Context context, FieldPlayer fieldPlayer) {
        this(context, fieldPlayer, FieldCoordinates.create(0, 0));

    }

    public FieldPlayerView(Context context, FieldPlayer fieldPlayer, FieldCoordinates fieldCoordinates) {
        super(context);
        this.fp = fieldPlayer;
        this.fc = fieldCoordinates;
        init();
    }

    public FieldPlayer getFieldPlayer() {
        return fp;
    }

    public void setPlayer(FieldPlayer fieldPlayer) {
        this.fp = fieldPlayer;
        init();
    }

    public FieldCoordinates getCoords() {
        return position(getFootballFieldLayout(), getX() + getWidthDelta(), getY() + getHeightDelta()).getCoords();
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
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        FootballFieldLayout parent = getFootballFieldLayout();
        if (parent.getWidth() > parent.getHeight()) {
            width = height = parent.getHeight() / 7; // At least 5 player in each column
        } else {
            width = height = parent.getWidth() / 12; // At least 4 colums
        }

        // http://stackoverflow.com/questions/13394181/inflated-children-of-custom-linearlayout-dont-show-when-overriding-onmeasure
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

        FieldPosition fposition = FieldPosition.createFromXY(parent, fc.x(), fc.y());
        fposition = parent.rectifyPosition(this, fposition);

        setX(fposition.getXinPx());
        setY(fposition.getYinPx());
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    protected FootballFieldLayout getFootballFieldLayout(){
        return (FootballFieldLayout) this.getParent();
    }

    protected void invertCoords(){
        setCoords(getCoords().inverse());
    }

    protected void setCoords(FieldCoordinates fCoordinates){
        fc = fCoordinates;
        init();
    }

    private void init() {
        removeAllViews();
        setOrientation(LinearLayout.VERTICAL);
        setId(R.id.player_undefined);
        if (fp.getTeam() != null) {
            setBackgroundColor(fp.getTeam().getBackGroundColor());
            numberTextColor = fp.getTeam().getTextColor();
        }
        addNumber(fp.getNumber());
        addName(fp.getName());
    }

    private void addName(String shortName) {
        TextView tv = new TextView(getContext());
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.parseColor("#000000"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        tv.setText(shortName);

        addView(tv);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = 0;
        layoutParams.weight = totalWeight - numberWeight;

        tv.setLayoutParams(layoutParams);
    }

    private void addNumber(String number) {
        TextView tv = new TextView(getContext());
        addView(tv);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();

        //layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        final float scale = getContext().getResources().getDisplayMetrics().density;
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = 0;
        layoutParams.weight = numberWeight;

        tv.setText(number);
        tv.setTextColor(numberTextColor);
        tv.setLayoutParams(layoutParams);
        tv.setGravity(Gravity.CENTER);
        //tv.setBackgroundColor(Color.parseColor("#66000000"));


    }


    // TODO Can I private these methods?
    protected int getWidthDelta() {
        return width / 2;
    }

    protected int getHeightDelta() {
        return height / 2;
    }

}