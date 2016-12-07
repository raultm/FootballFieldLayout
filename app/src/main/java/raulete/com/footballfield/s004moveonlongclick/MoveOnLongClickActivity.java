package raulete.com.footballfield.s004moveonlongclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FootballFieldLayout;

public class MoveOnLongClickActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_004_move_on_long_click);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        addPlayer();
    }

    public void addPlayer() {
        footballFieldLayout.addPlayerLocal(BaseFieldPlayer.messi());
    }
}
