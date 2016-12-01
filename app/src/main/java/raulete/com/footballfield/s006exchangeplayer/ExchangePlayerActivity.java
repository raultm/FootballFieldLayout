package raulete.com.footballfield.s006exchangeplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldPosition;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class ExchangePlayerActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    private final FieldPlayer messi = new BaseFieldPlayer("Messi", "10");
    private final FieldPlayer neymar = new BaseFieldPlayer("Neymar", "11");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_006_exchange_player);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);

        addPlayer();
    }

    public void addPlayer() {
        footballFieldLayout.addPlayer(messi);
    }

    public void exchangePlayer(View view )
    {
        footballFieldLayout.exchange(messi, neymar);
    }
}
