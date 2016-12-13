package raulete.com.footballfield.s015teamimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raulete.com.footballfield.BaseFieldImageLoader;
import raulete.com.footballfield.R;
import raulete.com.footballfieldlayout.FieldCoordinates;
import raulete.com.footballfieldlayout.FootballFieldLayout;

import static raulete.com.footballfield.BaseFieldPlayer.fcb;
import static raulete.com.footballfield.BaseFieldPlayer.player;
import static raulete.com.footballfield.BaseFieldPlayer.rmd;

public class TeamImageActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_015_team_image);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);

        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);

        footballFieldLayout.setBoundariesHalfField();

        footballFieldLayout.setImageLoader(new BaseFieldImageLoader());
    }

    public void changeField(View view){
        footballFieldLayout.changeField();
    }

    public void addLocal(View view){
        footballFieldLayout.addPlayerLocal(player("Ter Stegen" , "1" , fcb), FieldCoordinates.create( 5,50));
    }

    public void addGuest(View view){
        footballFieldLayout.addPlayerGuest(player("Keylor"    , "1" , rmd), FieldCoordinates.create( 5,50).inverse());
    }


}
