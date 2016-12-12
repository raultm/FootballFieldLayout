package raulete.com.footballfield;

import android.graphics.Color;

import raulete.com.footballfieldlayout.FieldTeam;


/**
 * Created by raulete on 18/11/16.
 */

public class BaseFieldTeam implements FieldTeam {

    private final String name;
    private final String shortname;
    private final String imageUrl;
    private final int textColor;
    private final int bgColor;


    public final static BaseFieldTeam sam(){
        return create("C.P. San Miguel", "SAM", "http://i.imgur.com/1ksNsIk.png", "#88CCCCCC");
    }


    public final static BaseFieldTeam eud(){
        return create("Extremadura UD", "EUD", "http://i.imgur.com/1ksNsIk.png", "#883333FF");
    }

    public final static BaseFieldTeam fcb(){
        return create("FC Barcelona", "FCB", "", "#883333FF");
    }

    public final static BaseFieldTeam rmd(){
        return create("Real Madrid", "RMD", "", "#88FFFFFF");
    }

    public final static BaseFieldTeam atm(){
        return create("Atlético de Madrid", "ATM", "", "#88FF3333");
    }


    public final static BaseFieldTeam create(String name, String shortname, String imageUrl, String color){
        return new BaseFieldTeam(name, shortname, imageUrl, color);
    }

    public BaseFieldTeam(String name, String shortname, String imageUrl, String color) {
        this.name = name;
        this.shortname = shortname;
        this.imageUrl = imageUrl;
        this.bgColor = Color.parseColor(color);
        this.textColor = invertColor(bgColor);
    }

    private int invertColor(int bgColor) {
        // http://stackoverflow.com/questions/7427141/how-to-get-rgb-value-from-hexadecimal-color-code-in-java
        int r = (bgColor >> 16) & 0xFF;
        int g = (bgColor >> 8) & 0xFF;
        int b = (bgColor >> 0) & 0xFF;

        // http://stackoverflow.com/questions/14686818/how-to-set-auto-inverting-color-of-text-in-textview
        int ir = 255 - r;
        int ig = 255 - g;
        int ib = 255 - b;

        return Color.rgb(ir,ig,ib);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getShortName() {
        return shortname;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int getTextColor() {
        return textColor;
    }

    @Override
    public int getBackGroundColor() {
        return bgColor;
    }
}
