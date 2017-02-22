package raulete.com.footballfieldlayout;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import static raulete.com.footballfieldlayout.FFL.coords;

/**
 * Created by raulete on 23/11/16.
 */
public class FieldPosition {

    // X,Y reference to 0,1 position of info in int[2] coordinates
    private final static int X = 0;
    private final static int Y = 1;

    private final float fieldRawX;
    private final float fieldRawY;

    private final float fieldX;
    private final float fieldY;

    private final float fieldWidth;
    private final float fieldHeight;

    private final float positionX;
    private final float positionY;


    public static FieldPosition createFromEvent(FootballFieldLayout footballFieldLayout, MotionEvent event) {
        return createFromRawXY(footballFieldLayout, event.getRawX(), event.getRawY());
    }

    public static final FieldPosition createFromRawXY(FootballFieldLayout ffl, float x, float y) {
        Rect rectf = new Rect();
        ffl.getLocalVisibleRect(rectf);
        int[] coordinates = new int[2];
        ffl.getLocationOnScreen(coordinates);

        float positionX = x * 100 / ffl.getWidth();
        float positionY = y * 100 / ffl.getHeight();

        return createFromXY(ffl, positionX, positionY);
    }

    private static String info(float x, float y, float positionX, float positionY){
        return "Raw (" + x + "," + y + ")" + "/" + "% (" + positionX + "," + positionY + ")";
    }

    public static final FieldPosition createFromXY(FootballFieldLayout ffl, float x, float y) {
        return new FieldPosition(ffl, x, y);
    }

    private FieldPosition(FootballFieldLayout footballFieldLayout, float x, float y) {
        int[] coordinates = new int[2];
        footballFieldLayout.getLocationOnScreen(coordinates);

        fieldRawX = coordinates[0];
        fieldRawY = coordinates[1];

        fieldX = footballFieldLayout.getX();
        fieldY = footballFieldLayout.getY();

        fieldHeight = footballFieldLayout.getHeight();
        fieldWidth = footballFieldLayout.getWidth();

        positionX = x;
        positionY = y;
    }

    public String info() {
        return "Field Coords/Dimen: (" + fieldX + "," + fieldY + ")/(" + fieldWidth + "," + fieldHeight + ")"
                + "- %position: (" + positionX + "," + positionY + ")";
    }

    public FieldCoordinates getCoords() {
        return coords(positionX, positionY );
    }

    public String getFieldDimen() {
        return "Field Dimen: (" + fieldWidth + "," + fieldHeight + ")";
    }

    public String getFieldCoords() {
        return "Field Coords: (" + fieldRawX + "," + fieldRawY + ")(" + fieldX + "," + fieldY + ")";
    }

    public float getX() {
        return positionX;
    }

    public float getY() {
        return positionY;
    }

    public float getXinPx(){
        return getX() * fieldWidth / 100;
    }

    public float getYinPx(){
        return getY() * fieldHeight / 100;
    }

}
