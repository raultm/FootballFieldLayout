package raulete.com.footballfield.s002addplayer;

import raulete.com.footballfield.custom.FieldPlayer;

/**
 * Created by raulete on 18/11/16.
 */

public class BaseFieldPlayer implements FieldPlayer {

    private final String name;
    private final String number;

    public BaseFieldPlayer(String name, String number) {
        this.name = name;
        this.number = number;
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
}
