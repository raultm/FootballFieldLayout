package raulete.com.footballfield.s012adplayercustomposition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldCoordinates;
import raulete.com.footballfieldlayout.FieldPlayer;
import raulete.com.footballfieldlayout.FootballFieldLayout;

public class CustomPositionActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    private final FieldPlayer messi = BaseFieldPlayer.create("Messi", "10");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_012_custom_position);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);
    }

    public void addPlayer(View view) {
        footballFieldLayout.addPlayerLocal(messi, FieldCoordinates.create(50f, 50f));
    }


}
