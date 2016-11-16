package raulete.com.footballfield.custom;

import java.util.ArrayList;

/**
 * Created by raulete on 8/11/16.
 */
public class FieldPlayerCollection {

    ArrayList<FieldPlayer> list = new ArrayList<>();

    ArrayList<FieldPlayerCollection.OnChangesListener> listeners = new ArrayList<>();

    public int size()
    {
        return list.size();
    }

    public boolean add(FieldPlayer player)
    {
        for (FieldPlayerCollection.OnChangesListener listener : listeners)
        {
            listener.change(this);
        }
        return list.add(player);
    }

    public void addListener(FootballFieldLayout footballFieldLayout) {
        listeners.add(footballFieldLayout);
    }


    public interface OnChangesListener {
        void change(FieldPlayerCollection fpc);
    }
}
