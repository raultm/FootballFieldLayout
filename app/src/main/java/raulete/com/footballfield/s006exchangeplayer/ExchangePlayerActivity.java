package raulete.com.footballfield.s006exchangeplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldPlayer;
import raulete.com.footballfieldlayout.FootballFieldLayout;

public class ExchangePlayerActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    private final FieldPlayer messi = BaseFieldPlayer.messi();
    private final FieldPlayer neymar = BaseFieldPlayer.neymar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_006_exchange_player);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);

        addPlayer();
    }

    public void addPlayer() {
        footballFieldLayout.addPlayerLocal(messi);
    }

    public void exchangePlayer(View view )
    {
        footballFieldLayout.exchange(messi, neymar);
    }
}
