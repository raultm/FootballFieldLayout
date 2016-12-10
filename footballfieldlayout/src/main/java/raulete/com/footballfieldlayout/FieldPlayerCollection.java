package raulete.com.footballfieldlayout;

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

    public void exchange(FieldPlayer playerFromFieldToBench, FieldPlayer playerFromBenchToField) {
        FieldPlayerView fpv = mapViews.get(playerFromFieldToBench);
        FieldCoordinates fc = fpv.getCoords();
        fpv.setPlayer(playerFromBenchToField);
        add(fpv);
        fpv.setCoords(fc);

        mapViews.remove(playerFromFieldToBench);
    }

    public void invertPositions() {
        for(Map.Entry<FieldPlayer, FieldPlayerView> entry : mapViews.entrySet()) {
            FieldPlayer fp = entry.getKey();
            FieldPlayerView fpv = entry.getValue();
            fpv.invertCoords();
        }
    }
}
