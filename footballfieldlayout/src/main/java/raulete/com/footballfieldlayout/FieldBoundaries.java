package raulete.com.footballfieldlayout;

/**
 * Created by raulete on 9/12/16.
 */

public class FieldBoundaries {

    private final int xMin;
    private final int yMin;
    private final int xMax;
    private final int yMax;

    public final static int BOUNDARIES_NONE = 100;
    public final static int BOUNDARIES_FIELD = 200;
    public final static int BOUNDARIES_HALF_FIELD = 300;

    public final static FieldBoundaries getBoundaries(int boundariesType, FootballFieldLayout ffl, FieldPlayerView fpv){
        if(boundariesType == BOUNDARIES_NONE){
            return new FieldBoundaries(
                    Integer.MIN_VALUE, Integer.MIN_VALUE,
                    Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        if(boundariesType == BOUNDARIES_FIELD){
            return new FieldBoundaries(
                    0 + fpv.getWidthDelta(), 0 + fpv.getHeightDelta(),
                    ffl.getWidth() - fpv.getWidthDelta(), ffl.getHeight() - fpv.getHeightDelta());
        }

        if(boundariesType == BOUNDARIES_HALF_FIELD){
            if(ffl.isLeftSide(fpv)){
                return halfFieldBoundariesLeftSide(ffl, fpv);
            }
            if(ffl.isRightSide(fpv)){
                return halfFieldBoundariesRightSide(ffl, fpv);
            }
        }
        return new FieldBoundaries(0,0,0,0);
    }

    private static FieldBoundaries halfFieldBoundariesLeftSide(FootballFieldLayout ffl, FieldPlayerView fpv){
        return new FieldBoundaries(
                0 + fpv.getWidthDelta(), 0 + fpv.getHeightDelta(),
                ffl.getWidth()/2 - fpv.getWidthDelta(), ffl.getHeight() - fpv.getHeightDelta());
    }

    private static FieldBoundaries halfFieldBoundariesRightSide(FootballFieldLayout ffl, FieldPlayerView fpv){
        return new FieldBoundaries(
                ffl.getWidth()/2 + fpv.getWidthDelta(), 0 + fpv.getHeightDelta(),
                ffl.getWidth() - fpv.getWidthDelta(), ffl.getHeight() - fpv.getHeightDelta());
    }


    private FieldBoundaries(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }


    public int getxMin() {
        return xMin;
    }

    public int getyMin() {
        return yMin;
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }
}
