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

    private final FieldPlayer messi = BaseFieldPlayer.messi();
    private final FieldPlayer ronaldo = BaseFieldPlayer.ronaldo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_013_player_boundaries);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);
    }

    public void addLocalPlayer(View v){
        footballFieldLayout.addPlayerLocal(ronaldo, FieldCoordinates.create(0,0));
    }

    public void addLocalPlayerMidfield(View v){
        footballFieldLayout.addPlayerLocal(ronaldo, FieldCoordinates.create(50,50));
    }

    public void addGuestPlayerMidfield(View v){
        footballFieldLayout.addPlayerGuest(messi, FieldCoordinates.create(50,50));
    }

    public void addGuestPlayer(View v){
        footballFieldLayout.addPlayerGuest(messi, FieldCoordinates.create(100, 100));
    }

    public void setBoundariesNone(View v) {
        footballFieldLayout.setBoundariesNone();
    }

    public void setBoundariesField(View v){
        footballFieldLayout.setBoundariesField();
    }

    public void setBoundariesHalfField(View v){
        footballFieldLayout.setBoundariesHalfField();
    }

}
