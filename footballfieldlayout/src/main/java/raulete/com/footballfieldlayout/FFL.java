package raulete.com.footballfieldlayout;

/**
 * Created by raulete on 10/12/16.
 */

public class FFL {

    public final static FieldCoordinates coords(float x, float y){
        return FieldCoordinates.create(x, y);
    }

    public final static FieldPosition position(FootballFieldLayout ffl, float x, float y){
        return FieldPosition.createFromRawXY(ffl, x, y);
    }
}
