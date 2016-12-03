package raulete.com.footballfield.custom;

import java.lang.reflect.Field;

/**
 * Created by raulete on 2/12/16.
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

}
