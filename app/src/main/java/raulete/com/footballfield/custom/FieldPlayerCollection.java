package raulete.com.footballfield.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raulete on 8/11/16.
 */
public class FieldPlayerCollection {

    ArrayList<FieldPlayer> list = new ArrayList<>();

    Map<FieldPlayer, FieldPlayerView> mapViews = new HashMap<FieldPlayer, FieldPlayerView>();

    public boolean add(FieldPlayer player) {
        return list.add(player);
    }

    public boolean add(FieldPlayerView playerView) {
        return list.add(playerView.getFieldPlayer()) && map(playerView);
    }

    public FieldPlayer first() {
        if(list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

    public boolean map(FieldPlayer player, FieldPlayerView view){
        return mapViews.put(player, view) == view;
    }

    public boolean map(FieldPlayerView view){
        return map(view.getFieldPlayer(), view);
    }

    public FieldPlayerView getView(FieldPlayer player) {
        return mapViews.get(player);
    }

    public void exchange(FieldPlayer playerFromFieldToBench, FieldPlayer playerFromBenchToField) {
        FieldPlayerView fpv = mapViews.get(playerFromFieldToBench);
        fpv.setPlayer(playerFromBenchToField);
        add(fpv);
        mapViews.remove(playerFromFieldToBench);
    }
}
