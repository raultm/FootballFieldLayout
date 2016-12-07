package raulete.com.footballfieldlayout;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by raulete on 16/11/16.
 */

public class FieldTeamView extends ImageView {

    private FieldTeam ft;
    private FieldCoordinates fc;
    private FieldPosition fposition;

    // Settled onMeasure()
    private int width;
    private int height;

    public FieldTeamView(Context context, int resId, FieldTeam fieldTeam) {
        this(context, resId, fieldTeam, FieldCoordinates.create(0,0));

    }

    public FieldTeamView(Context context, int resId, FieldTeam fieldTeam, FieldCoordinates fieldCoordinates) {
        super(context);
        this.ft = fieldTeam;
        this.fc = fieldCoordinates;
        setId(resId);
    }

    public FieldTeam getFieldTeam() {
        return ft;
    }

    /**
     * Ask all children to measure themselves and compute the measurement of this
     * layout based on the children.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View parent = (View)this.getParent();
        width = parent.getWidth() / 4;
        height = width;
        fposition = FieldPosition.createFromXY((FootballFieldLayout) getParent(), fc.x(), fc.y());
        // http://stackoverflow.com/questions/13394181/inflated-children-of-custom-linearlayout-dont-show-when-overriding-onmeasure
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        int left = (int)fposition.getXinPx() - (width / 2);
        int right = (int)fposition.getYinPx() - (height / 2);
        if(left < 0){ left = 0; }
        if(right < 0){ right = 0; }

        params.leftMargin = left;
        params.topMargin = right;
    }

}