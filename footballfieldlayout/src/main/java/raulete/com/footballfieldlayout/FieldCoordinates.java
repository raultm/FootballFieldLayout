package raulete.com.footballfieldlayout;

/**
 * Coordinates to use over the field.
 *  X Axis - 0, 100
 *  Y Axis - 0, 100
 */

public class FieldCoordinates {

    public final float x;
    public final float y;

    public static final FieldCoordinates create(){
        return create(0,0);
    }

    public static final FieldCoordinates create(float x, float y){
        return new FieldCoordinates(x, y);
    }

    private FieldCoordinates(float x, float y){
        this.x = x;
        this.y = y;
    }

    public int x(){
        return (int)x;
    }

    public int y(){
        return (int)y;
    }

    public FieldCoordinates inverse() {
        return create(100 - x, 100 - y);
    }

    @Override
    public String toString(){
        return "(" + x +", " + y + ")";
    }
}
