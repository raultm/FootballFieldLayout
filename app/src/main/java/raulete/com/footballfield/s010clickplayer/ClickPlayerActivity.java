package raulete.com.footballfield.s010clickplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldPlayerView;
import raulete.com.footballfield.custom.FieldPosition;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class ClickPlayerActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_010_click_player);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        //footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);

        footballFieldLayout.setOnClickPlayerListener(new FootballFieldLayout.OnPlayerClickCallback(){

            @Override
            public void click(FieldPlayerView fPlayer) {
                Toast.makeText(ClickPlayerActivity.this, fPlayer.getFieldPlayer().getShortName(), Toast.LENGTH_LONG).show();
            }
        });

        addPlayer();
    }

    public void addPlayer() {
        footballFieldLayout.addPlayerLocal(BaseFieldPlayer.messi());
    }
}