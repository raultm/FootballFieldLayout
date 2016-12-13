package raulete.com.footballfield.s014changefield;

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

public class ChangeFieldActivity extends AppCompatActivity {

    FootballFieldLayout footballFieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_014_change_field);

        footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_field);
        footballFieldLayout.setActionToActivateOnTouchListener(FootballFieldLayout.PLAYER_MOVE_ON_ADDED);
        footballFieldLayout.setBoundariesHalfField();
        footballFieldLayout.setImageLoader(new BaseFieldImageLoader());
    }

    public void changeField(View view){
        footballFieldLayout.changeField();
    }

    public void addPlayer(View view){
        footballFieldLayout.addPlayerLocal(player("Ter Stegen" , "1" , fcb), FieldCoordinates.create( 20,50));
    }

    public void addTwoTeams(View view){
        addLocalTeam();
        addGuestTeam();
    }

    public void addLocalTeam(){
        footballFieldLayout.addPlayerLocal(player("Ter Stegen" , "1" , fcb), FieldCoordinates.create( 5,50));
        footballFieldLayout.addPlayerLocal(player("S. Roberto" , "20", fcb), FieldCoordinates.create(17,84));
        footballFieldLayout.addPlayerLocal(player("Piqué"      , "3" , fcb), FieldCoordinates.create(17,62));
        footballFieldLayout.addPlayerLocal(player("Mascherano" , "14", fcb), FieldCoordinates.create(17,38));
        footballFieldLayout.addPlayerLocal(player("J. Alba"    , "18", fcb), FieldCoordinates.create(17,16));
        footballFieldLayout.addPlayerLocal(player("Rakitic"    , "4" , fcb), FieldCoordinates.create(30,80));
        footballFieldLayout.addPlayerLocal(player("Busquets"   , "5" , fcb), FieldCoordinates.create(30,50));
        footballFieldLayout.addPlayerLocal(player("André Gomes", "7" , fcb), FieldCoordinates.create(30,20));
        footballFieldLayout.addPlayerLocal(player("Messi"      , "10", fcb), FieldCoordinates.create(42,80));
        footballFieldLayout.addPlayerLocal(player("Suárez"     , "9" , fcb), FieldCoordinates.create(42,50));
        footballFieldLayout.addPlayerLocal(player("Neymar"     , "11", fcb), FieldCoordinates.create(42,20));
    }

    public void addGuestTeam(){
        footballFieldLayout.addPlayerGuest(player("Keylor"    , "1" , rmd), FieldCoordinates.create( 5,50).inverse());
        footballFieldLayout.addPlayerGuest(player("Marcelo"   , "12", rmd), FieldCoordinates.create(17,16).inverse());
        footballFieldLayout.addPlayerGuest(player("Ramos"     , "4" , rmd), FieldCoordinates.create(17,38).inverse());
        footballFieldLayout.addPlayerGuest(player("Varane"    , "5" , rmd), FieldCoordinates.create(17,62).inverse());
        footballFieldLayout.addPlayerGuest(player("Carvajal"  , "2" , rmd), FieldCoordinates.create(17,84).inverse());
        footballFieldLayout.addPlayerGuest(player("Ronaldo"   , "7" , rmd), FieldCoordinates.create(30,8).inverse());
        footballFieldLayout.addPlayerGuest(player("Kovacic"   , "16", rmd), FieldCoordinates.create(30,28).inverse());
        footballFieldLayout.addPlayerGuest(player("Isco"      , "22", rmd), FieldCoordinates.create(30,50).inverse());
        footballFieldLayout.addPlayerGuest(player("Modric"    , "19", rmd), FieldCoordinates.create(30,72).inverse());
        footballFieldLayout.addPlayerGuest(player("L. Vázquez", "17", rmd), FieldCoordinates.create(30,92).inverse());
        footballFieldLayout.addPlayerGuest(player("Benzema"   , "9" , rmd), FieldCoordinates.create(42,50).inverse());
    }
}
