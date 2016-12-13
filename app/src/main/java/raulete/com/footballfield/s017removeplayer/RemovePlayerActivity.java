package raulete.com.footballfield.s017removeplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldImageLoader;
import raulete.com.footballfield.BaseFieldPlayer;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldPlayer;
import raulete.com.footballfieldlayout.FootballFieldLayout;

import static raulete.com.footballfield.BaseFieldPlayer.DEFENSE;
import static raulete.com.footballfield.BaseFieldPlayer.FORWARD;
import static raulete.com.footballfield.BaseFieldPlayer.GOALKEEPER;
import static raulete.com.footballfield.BaseFieldPlayer.MIDFIELDER;
import static raulete.com.footballfield.BaseFieldPlayer.eud;
import static raulete.com.footballfield.BaseFieldPlayer.player;
import static raulete.com.footballfield.BaseFieldPlayer.sam;

public class RemovePlayerActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    FieldPlayer messi = BaseFieldPlayer.messi();
    FieldPlayer neymar = BaseFieldPlayer.neymar();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_017_remove_player);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);
        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);
        footballFieldLayout.setBoundariesHalfField();
        footballFieldLayout.setImageLoader(new BaseFieldImageLoader());
        footballFieldLayout.addLocal(messi);
    }

    public void addTwo(View view){
        footballFieldLayout.addLocal(messi, neymar);
    }

    public void removeMessi(View view){
        footballFieldLayout.remove(messi);
    }

    public void removeNeymar(View view){
        footballFieldLayout.remove(neymar);
    }
}
