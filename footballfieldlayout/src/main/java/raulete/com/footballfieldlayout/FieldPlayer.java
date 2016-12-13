package raulete.com.footballfieldlayout;

/**
 * Created by raulete on 15/11/16.
 */
public interface FieldPlayer {
    String getNumber();

    String getName();

    FieldTeam getTeam();

    boolean isGoalkeeper();

    boolean isDefense();

    boolean isMidfielder();

    boolean isForward();
}
