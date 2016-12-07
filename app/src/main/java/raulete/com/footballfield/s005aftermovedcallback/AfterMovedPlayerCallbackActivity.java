package raulete.com.footballfield.s005aftermovedcallback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldPlayerView;
import raulete.com.footballfieldlayout.FieldPosition;
import raulete.com.footballfieldlayout.FootballFieldLayout;

public class AfterMovedPlayerCallbackActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_005_after_moved_player_callback);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);

        final TextView fieldCoords = (TextView) findViewById(R.id.info_field_coords);
        final TextView fieldDimen = (TextView) findViewById(R.id.info_field_dimen);
        final TextView pointCoords = (TextView) findViewById(R.id.info_position);

        footballFieldLayout.setOnPlayerActionsCallback(new FootballFieldLayout.OnPlayerActionsCallback() {
            @Override
            public boolean moving(FieldPlayerView fPlayer, FieldPosition fPosition) {
                if(fPosition.getX() > 50){ return false; }
                return true;
            }

            @Override
            public void moved(FieldPlayerView fp, FieldPosition fieldPosition) {
               fieldCoords.setText(fieldPosition.getFieldCoords());
               fieldDimen.setText(fieldPosition.getFieldDimen());
               pointCoords.setText(fieldPosition.getCoords());
            }
        });

        addPlayer();
    }

    public void addPlayer() {
        footballFieldLayout.addPlayerLocal(BaseFieldPlayer.messi());
    }
}
