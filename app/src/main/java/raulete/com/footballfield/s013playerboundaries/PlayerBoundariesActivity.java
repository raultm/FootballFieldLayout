package raulete.com.footballfield.s013playerboundaries;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldCoordinates;
import raulete.com.footballfieldlayout.FieldPlayer;
import raulete.com.footballfieldlayout.FootballFieldLayout;

public class PlayerBoundariesActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    private final FieldPlayer messi = BaseFieldPlayer.create("Messi", "10");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_013_player_boundaries);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);

        addPlayerPositionZeroZero();

    }

    public void addPlayerPositionZeroZero() {
        footballFieldLayout.addPlayerLocal(messi);
    }


}
