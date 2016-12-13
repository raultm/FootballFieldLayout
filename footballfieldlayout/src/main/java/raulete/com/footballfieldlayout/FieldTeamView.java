package raulete.com.footballfieldlayout;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by raulete on 16/11/16.
 */

public class FieldTeamView extends ImageView {

    private FieldCoordinates fc;
    private FieldPosition fposition;

    // Settled onMeasure()
    private int width;
    private int height;

    public FieldTeamView(Context context, int resId) {
        this(context, resId, FieldCoordinates.create(0,0));

    }

    public FieldTeamView(Context context, int resId, FieldCoordinates fieldCoordinates) {
        super(context);
        this.fc = fieldCoordinates;
        setAlpha(0.4f);
        setId(resId);
    }

    /**
     * Ask all children to measure themselves and compute the measurement of this
     * layout based on the children.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View parent = (View)this.getParent();
        height = width = parent.getWidth() / 4;
        fposition = FieldPosition.createFromXY((FootballFieldLayout) getParent(), fc.x(), fc.y());
        // http://stackoverflow.com/questions/13394181/inflated-children-of-custom-linearlayout-dont-show-when-overriding-onmeasure
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        int left = (int)fposition.getXinPx() - (width / 2);
        int top = (int)fposition.getYinPx() - (height / 2);
        if(left < 0){ left = 0; }
        if(top < 0){ top = 0; }

        setX(left);
        setY(top);
    }

}