package raulete.com.footballfield.s008teamcolor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class TeamColorActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    private final FieldPlayer messi = BaseFieldPlayer.messi();
    private final FieldPlayer ronaldo = BaseFieldPlayer.ronaldo();
    private final FieldPlayer koke = BaseFieldPlayer.koke();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_008_team_color);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);
    }

    public void addRonaldoGuest(View view) { footballFieldLayout.addPlayerGuest(ronaldo);}

    public void addKokeGuest(View view) { footballFieldLayout.addPlayerGuest(koke);}

    public void addMessiLocal(View view) { footballFieldLayout.addPlayerLocal(messi);}

}
