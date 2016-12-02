package raulete.com.footballfield.custom;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import raulete.com.footballfield.R;

/**
 * Created by raulete on 16/11/16.
 */

public class FieldPlayerView extends LinearLayout {

    private FieldPlayer fp;
    private FieldCoordinates fc;

    public FieldPlayerView(Context context, FieldPlayer fieldPlayer) {
        this(context, fieldPlayer, FieldCoordinates.create(0,0));

    }

    public FieldPlayerView(Context context, FieldPlayer fieldPlayer, FieldCoordinates fieldCoordinates) {
        super(context);
        this.fp = fieldPlayer;
        this.fc = fieldCoordinates;
        /*
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        params.setMargins(fieldCoordinates.x(), fieldCoordinates.y(), 0, 0);
        setLayoutParams(params);
                */
        draw();
    }

    private void draw() {
        removeAllViews();
        setOrientation(LinearLayout.VERTICAL);
        setId(R.id.player_undefined);
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
        //layoutParams.addRule(RelativeLayout.ALIGN_TOP, RelativeLayout.TRUE);
        //layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, RelativeLayout.TRUE);
        layoutParams.width = dip2px(40);
        layoutParams.height = dip2px(10);

        tv.setLayoutParams(layoutParams);
    }

    private void addNumber(String number) {
        TextView tv = new TextView(getContext());
        addView(tv);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();

        //layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        final float scale = getContext().getResources().getDisplayMetrics().density;
        layoutParams.width = dip2px(40);
        layoutParams.height = dip2px(30);

        tv.setText(number);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setLayoutParams(layoutParams);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.parseColor("#66000000"));


    }

    private int dip2px(int dips) {
        // final float scale = getContext().getResources().getDisplayMetrics().density;
        // (int) (dips * scale + 0.5f);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, getResources().getDisplayMetrics());

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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setPlayer(FieldPlayer fieldPlayer) {
        this.fp = fieldPlayer;
        draw();
    }

    public FieldCoordinates getFieldCoordinates(){
        return fc;
    }
}


/*
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="0dip"
    android:layout_weight="1">
    <TextView
            android:id="@+id/player_in_field_number"
            android:layout_width="40dip"
            android:layout_height="30dip"
            android:text="01"
            android:layout_centerInParent="true"
            android:gravity="center"
            android:background="#66000000"/>
    <TextView
        android:id="@+id/player_in_field_name"
        android:layout_below="@+id/player_in_field_number"
        android:layout_width="40dip"
        android:layout_height="10dip"
        android:text="01"
        android:layout_centerInParent="true"
        android:gravity="center"
        android:textSize="8sp"
        android:background="#000000"/>

</RelativeLayout>
 */