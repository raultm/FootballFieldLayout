package raulete.com.footballfield.s005aftermovedcallback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldPosition;
import raulete.com.footballfield.custom.FootballFieldLayout;

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
            public void moved(FieldPlayer fp, FieldPosition fieldPosition) {
               fieldCoords.setText(fieldPosition.getFieldCoords());
               fieldDimen.setText(fieldPosition.getFieldDimen());
               pointCoords.setText(fieldPosition.getCoords());
            }
        });

        addPlayer();
    }

    public void addPlayer() {
        footballFieldLayout.addPlayer(new BaseFieldPlayer("Messi", "10"));
    }
}
