package raulete.com.footballfield;


import raulete.com.footballfieldlayout.FieldPlayer;
import raulete.com.footballfieldlayout.FieldTeam;

/**
 * Created by raulete on 18/11/16.
 */

public class BaseFieldPlayer implements FieldPlayer {

    public static final BaseFieldTeam fcb = BaseFieldTeam.fcb();
    public static final BaseFieldTeam rmd = BaseFieldTeam.rmd();
    public static final BaseFieldTeam atm = BaseFieldTeam.atm();

    private final String name;
    private final String number;
    private FieldTeam team;

    public static BaseFieldPlayer player(String name, String number, BaseFieldTeam team){
        return create(name, number, team);
    }

    public final static BaseFieldPlayer messi(){
        return create("Messi", "10", fcb);
    }

    public final static BaseFieldPlayer neymar(){
        return create("Neymar", "11", fcb);
    }

    public final static BaseFieldPlayer ronaldo(){
        return create("Ronaldo", "7", rmd);
    }

    public final static BaseFieldPlayer koke(){
        return create("Koke", "6", atm);
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
