package raulete.com.footballfield.s016wholeteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldImageLoader;
import raulete.com.footballfield.BaseFieldTeam;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldCoordinates;
import raulete.com.footballfieldlayout.FootballFieldLayout;

import static raulete.com.footballfield.BaseFieldPlayer.DEFENSE;
import static raulete.com.footballfield.BaseFieldPlayer.FORWARD;
import static raulete.com.footballfield.BaseFieldPlayer.GOALKEEPER;
import static raulete.com.footballfield.BaseFieldPlayer.MIDFIELDER;
import static raulete.com.footballfield.BaseFieldPlayer.eud;
import static raulete.com.footballfield.BaseFieldPlayer.fcb;
import static raulete.com.footballfield.BaseFieldPlayer.player;
import static raulete.com.footballfield.BaseFieldPlayer.rmd;
import static raulete.com.footballfield.BaseFieldPlayer.sam;

public class WholeTeamActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_016_whole_team);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);
        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);
        footballFieldLayout.setBoundariesHalfField();
        footballFieldLayout.setImageLoader(new BaseFieldImageLoader());
    }

    public void changeField(View view){
        footballFieldLayout.changeField();
    }

    public void addLocal(View view){
        footballFieldLayout.addLocal(
                player("Saavedra"       , "1" , eud, GOALKEEPER),
                player("Sergi"          , "3" , eud, DEFENSE),
                player("Barragán"       , "5" , eud, DEFENSE),
                player("Pereira"        , "4" , eud, DEFENSE),
                player("Carlos Rubén"   , "2" , eud, DEFENSE),
                player("Javi Pérez"     , "10", eud, MIDFIELDER),
                player("Fall"           , "6" , eud, MIDFIELDER),
                player("Jose Manuel"    , "11", eud, MIDFIELDER),
                player("Pierre"         , "8" , eud, MIDFIELDER), // Kunde
                player("Dieguito"       , "7" , eud, MIDFIELDER),
                player("Willy"          , "9" , eud, FORWARD)
        );
    }

    public void addGuest(View view){
        footballFieldLayout.addGuest(
                player("Lidia"   , "25", sam, GOALKEEPER),
                player("Saray"   , "6" , sam, DEFENSE),
                player("Cristina", "23", sam, DEFENSE),
                player("Paula M.", "2" , sam, DEFENSE),
                player("Lia"     , "18", sam, DEFENSE),
                player("Amanda"  , "9" , sam, MIDFIELDER),
                player("Lucía F.", "7" , sam, MIDFIELDER),
                player("Lidia H.", "8" , sam, MIDFIELDER),
                player("Miranda" , "21", sam, MIDFIELDER),
                player("Rai"     , "11", sam, FORWARD),
                player("Paula F.", "24", sam, FORWARD)
        );
    }
}
