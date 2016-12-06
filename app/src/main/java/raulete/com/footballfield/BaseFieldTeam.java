package raulete.com.footballfield;

import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldTeam;

/**
 * Created by raulete on 18/11/16.
 */

public class BaseFieldTeam implements FieldTeam {

    private final String name;
    private final String shortname;
    private final String imageUrl;

    public final static BaseFieldTeam eud(){
        return create("Extremadura UD", "EUD", "http://i.imgur.com/1ksNsIk.png");
    }

    public final static BaseFieldTeam fcb(){
        return create("FC Barcelona", "FCB", "");
    }

    public final static BaseFieldTeam rmd(){
        return create("Real Madrid", "RMD", "");
    }

    public final static BaseFieldTeam atm(){
        return create("Atlético de Madrid", "ATM", "");
    }


    public final static BaseFieldTeam create(String name, String shortname, String imageUrl){
        return new BaseFieldTeam(name, shortname, imageUrl);
    }

    public BaseFieldTeam(String name, String shortname, String imageUrl) {
        this.name = name;
        this.shortname = shortname;
        this.imageUrl = imageUrl;
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
}
