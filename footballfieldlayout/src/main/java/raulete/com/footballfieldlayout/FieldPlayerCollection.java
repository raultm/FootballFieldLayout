package raulete.com.footballfieldlayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raulete on 8/11/16.
 */
public class FieldPlayerCollection {

    private Map<FieldPlayer, FieldPlayerView> mapViews = new HashMap<FieldPlayer, FieldPlayerView>();

    public boolean add(FieldPlayerView playerView) {
        return map(playerView);
    }

    public FieldPlayerView remove(FieldPlayer player){
        FieldPlayerView fpv = mapViews.remove(player);
        if(fpv != null && fpv.getParent() != null){
            fpv.getFootballFieldLayout().removeView(fpv);
        }
        return fpv;
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

    public int teammatesCount(FieldPlayer player) {
        int teammates = 0;
        for(FieldPlayer p : mapViews.keySet()){
            if(p.getTeam().equals(player.getTeam()) && !player.equals(p)){
                teammates++;
            }
        }
        return teammates;
    }

    public FieldPlayerView get(FieldPlayer player) {
        return mapViews.get(player);
    }

    private boolean map(FieldPlayerView view){
        return map(view.getFieldPlayer(), view);
    }

    private boolean map(FieldPlayer player, FieldPlayerView view){
        return mapViews.put(player, view) == view;
    }
}
