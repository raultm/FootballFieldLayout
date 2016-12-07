package raulete.com.footballfield.s011exchangesameteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class ExchangeSameTeamActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    private final FieldPlayer messi = BaseFieldPlayer.messi();
    private final FieldPlayer neymar = BaseFieldPlayer.neymar();
    private final FieldPlayer ronaldo = BaseFieldPlayer.ronaldo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_011_exchange_same_team);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        addMessi();
    }

    public void addMessi() {
        footballFieldLayout.addPlayerLocal(messi);
    }

    public void exchangeNeymar(View view )
    {
        footballFieldLayout.exchange(messi, neymar);
    }

    public void exchangeRonaldo(View view )
    {
        footballFieldLayout.exchange(messi, ronaldo);
    }
}
