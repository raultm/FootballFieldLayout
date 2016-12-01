package raulete.com.footballfield.custom;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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

        Rect rectf = new Rect();
        footballFieldLayout.getLocalVisibleRect(rectf);
        int[] coordinates = new int[2];
        footballFieldLayout.getLocationOnScreen(coordinates);

        Log.d("WIDTH        :", String.valueOf(rectf.width()));
        Log.d("HEIGHT       :", String.valueOf(rectf.height()));
        Log.d("left         :", String.valueOf(rectf.left));
        Log.d("right        :", String.valueOf(rectf.right));
        Log.d("top          :", String.valueOf(rectf.top));
        Log.d("bottom       :", String.valueOf(rectf.bottom));

        Log.d("x          :", String.valueOf(coordinates[X]));
        Log.d("y          :", String.valueOf(coordinates[Y]));

        Log.i("s005", "event (" + event.getRawX() + ", " + event.getRawY() +")");
        Log.i("s005", "event (" + event.getX() + ", " + event.getY() +")");
        Log.i("s005", "field (" + footballFieldLayout.getX() + ", " + footballFieldLayout.getY() +")");
        Log.i("s005", "field (" + footballFieldLayout.getWidth() + ", " + footballFieldLayout.getHeight() +")");
       // float positionX = (event.getRawX() - footballFieldLayout.getX()) * 100 / footballFieldLayout.getWidth();
        float positionX = (event.getRawX() - coordinates[X]) * 100 / footballFieldLayout.getWidth();
        //float positionY = (event.getRawY() - footballFieldLayout.getY()) * 100 / footballFieldLayout.getHeight();
        float positionY = (event.getRawY() - coordinates[Y]) * 100 / footballFieldLayout.getHeight();

        return new FieldPosition(footballFieldLayout, positionX, positionY);
    }

//    public static FieldPosition createFromFieldPlayerView(FootballFieldLayout footballFieldLayout, FieldPlayerView playerView) {
//        int[] fieldCoordinates = getCoordinates(footballFieldLayout);
//        int[] playerCoordinates = getCoordinates(playerView);
//
//        float positionX = (playerCoordinates[X] - fieldCoordinates[X]) * 100 / footballFieldLayout.getWidth();
//        float positionY = (playerCoordinates[Y] - fieldCoordinates[Y]) * 100 / footballFieldLayout.getHeight();
//
//        return new FieldPosition(footballFieldLayout, positionX, positionY);
//    }

    private static int[] getCoordinates(View view){
        int[] coordinates = new int[2];
        view.getLocationOnScreen(coordinates);
        return coordinates;
    }

    private FieldPosition(FootballFieldLayout footballFieldLayout, float x, float y)
    {
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

    public String getCoords(){
        return "Relative Coords: (" + positionX + "," + positionY + ")";
    }

    public String getFieldDimen(){
        return "Field Dimen: (" + fieldWidth + "," + fieldHeight + ")";
    }

    public String getFieldCoords(){
        return "Field Coords: (" + fieldRawX + "," + fieldRawY + ")(" + fieldX + "," + fieldY + ")";
    }

    public int getX() {
        return (int) positionX;
    }

    public int getY() {
        return (int) positionY;
    }


}
