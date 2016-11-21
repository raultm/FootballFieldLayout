package raulete.com.footballfield.s002addplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class AddPlayerActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_001_basic);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_basic);
    }

    public void addPlayer(View view) {
        footballFieldLayout.addPlayer(new BaseFieldPlayer("Messi", "10"));
    }
}
