package raulete.com.footballfield.custom;

import java.util.ArrayList;

/**
 * Created by raulete on 8/11/16.
 */
public class FieldPlayerCollection {

    ArrayList<FieldPlayer> list = new ArrayList<>();

    public boolean add(FieldPlayer player) {
        return list.add(player);
    }

    public FieldPlayer first() {
        if(list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

}
