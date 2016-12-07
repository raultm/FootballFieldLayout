package raulete.com.footballfieldlayout;

/**
 * Created by raulete on 15/11/16.
 */
public interface FieldPlayer {
    boolean isPositioned();

    String getNumber();

    String getShortName();

    FieldTeam getTeam();
}
