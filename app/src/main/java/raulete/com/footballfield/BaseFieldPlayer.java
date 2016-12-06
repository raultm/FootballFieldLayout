package raulete.com.footballfield;

import java.lang.reflect.Field;

import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldTeam;

/**
 * Created by raulete on 18/11/16.
 */

public class BaseFieldPlayer implements FieldPlayer {

    private final String name;
    private final String number;
    private FieldTeam team;

    public final static BaseFieldPlayer messi(){
        return create("Messi", "10", BaseFieldTeam.fcb());
    }

    public final static BaseFieldPlayer neymar(){
        return create("Neymar", "11", BaseFieldTeam.fcb());
    }

    public final static BaseFieldPlayer ronaldo(){
        return create("Ronaldo", "7", BaseFieldTeam.rmd());
    }

    public final static BaseFieldPlayer koke(){
        return create("Koke", "6", BaseFieldTeam.atm());
    }


    public final static BaseFieldPlayer create(String name, String number){
        return create(name, number, BaseFieldTeam.eud());
    }

    public final static BaseFieldPlayer create(String name, String number, FieldTeam team){
        return new BaseFieldPlayer(name, number, team);
    }

    private BaseFieldPlayer(String name, String number, FieldTeam team) {
        this.name = name;
        this.number = number;
        this.team = team;
    }

    @Override
    public boolean isPositioned() {
        return false;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getShortName() {
        return name;
    }

    @Override
    public FieldTeam getTeam() {
        return team;
    }
}
