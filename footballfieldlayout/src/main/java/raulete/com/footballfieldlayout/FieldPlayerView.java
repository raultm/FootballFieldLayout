package raulete.com.footballfieldlayout;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by raulete on 16/11/16.
 */

public class FieldPlayerView extends LinearLayout {

    private FieldPlayer fp;
    private FieldCoordinates fc;
    private FieldPosition fposition;

    private int totalWeight = 10;
    private int numberWeight = 7;

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

    private void init() {
        removeAllViews();
        setOrientation(LinearLayout.VERTICAL);
        setId(R.id.player_undefined);
        if (fp.getTeam() != null) {
            setBackgroundColor(fp.getTeam().getBackGroundColor());
            numberTextColor = fp.getTeam().getTextColor();
        }
        addNumber(fp.getNumber());
        addName(fp.getShortName());
    }

    public FieldPlayer getFieldPlayer() {
        return fp;
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
        FootballFieldLayout parent = (FootballFieldLayout) this.getParent();
        if (parent.getWidth() > parent.getHeight()) {
            width = height = parent.getHeight() / 7; // At least 5 player in each column
        } else {
            width = height = parent.getWidth() / 12; // At least 4 colums
        }

        // http://stackoverflow.com/questions/13394181/inflated-children-of-custom-linearlayout-dont-show-when-overriding-onmeasure
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

        fposition = FieldPosition.createFromXY(parent, fc.x(), fc.y());
        fposition = parent.rectifyPosition(this, fposition);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        params.leftMargin = (int) fposition.getXinPx();
        params.topMargin = (int) fposition.getYinPx();
    }

    public void setPlayer(FieldPlayer fieldPlayer) {
        this.fp = fieldPlayer;
        init();
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public float[] getCoords() {
        return new float[]{getX() + getWidthDelta(), getY() + getHeightDelta()};
    }

    public int getWidthDelta() {
        return width / 2;
    }

    public int getHeightDelta() {
        return height / 2;
    }


}