package raulete.com.footballfield.s007playerteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class PlayerTeamActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    private final FieldPlayer messi = BaseFieldPlayer.messi();
    private final FieldPlayer ronaldo = BaseFieldPlayer.ronaldo();
    private final FieldPlayer koke = BaseFieldPlayer.koke();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_007_player_team);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        addMessi();
    }

    public void addMessi() {
        footballFieldLayout.addPlayerLocal(messi);
    }

    public void addRonaldoGuest(View view) { footballFieldLayout.addPlayerGuest(ronaldo);}

    public void addKokeGuest(View view) { footballFieldLayout.addPlayerGuest(koke);}

    public void addKokeLocal(View view) { footballFieldLayout.addPlayerLocal(koke);}
}
