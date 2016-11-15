package raulete.com.footballfield.custom;

import java.util.ArrayList;

/**
 * Created by raulete on 8/11/16.
 */
public class FieldPlayerCollection {

    ArrayList<FieldPlayer> list = new ArrayList<>();

    public int size()
    {
        return list.size();
    }

    public boolean add(FieldPlayer player)
    {
        return list.add(player);
    }
}
