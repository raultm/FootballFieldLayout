package raulete.com.footballfield;


import raulete.com.footballfieldlayout.FieldPlayer;
import raulete.com.footballfieldlayout.FieldTeam;

/**
 * Created by raulete on 18/11/16.
 */

public class BaseFieldPlayer implements FieldPlayer {

    public static final BaseFieldTeam eud = BaseFieldTeam.eud();
    public static final BaseFieldTeam sam = BaseFieldTeam.sam();
    public static final BaseFieldTeam fcb = BaseFieldTeam.fcb();
    public static final BaseFieldTeam rmd = BaseFieldTeam.rmd();
    public static final BaseFieldTeam atm = BaseFieldTeam.atm();

    public static final String GOALKEEPER = "G";
    public static final String DEFENSE = "D";
    public static final String MIDFIELDER = "M";
    public static final String FORWARD = "F";

    private final String name;
    private final String number;
    private FieldTeam team;

    private String position = "";

    public static BaseFieldPlayer player(String name, String number, BaseFieldTeam team, String position){
        return create(name, number, team, position);
    }

    public static BaseFieldPlayer player(String name, String number, BaseFieldTeam team){
        return player(name, number, team, "");
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
        return create(name, number, team, "");
    }

    public final static BaseFieldPlayer create(String name, String number, FieldTeam team, String position){
        return new BaseFieldPlayer(name, number, team, position);
    }

    private BaseFieldPlayer(String name, String number, FieldTeam team, String position) {
        this.name = name;
        this.number = number;
        this.team = team;
        this.position = position;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public FieldTeam getTeam() {
        return team;
    }

    @Override
    public boolean isGoalkeeper() {
        return position.equals(GOALKEEPER);
    }

    @Override
    public boolean isDefense() {
        return position.equals(DEFENSE);
    }

    @Override
    public boolean isMidfielder() {
        return position.equals(MIDFIELDER);
    }

    @Override
    public boolean isForward() {
        return position.equals(FORWARD);
    }
}
