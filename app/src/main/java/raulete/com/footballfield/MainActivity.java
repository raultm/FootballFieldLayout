package raulete.com.footballfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import raulete.com.footballfield.s001emptyfield.BasicFieldActivity;
import raulete.com.footballfield.s002addplayer.AddPlayerActivity;
import raulete.com.footballfield.s003moveplayer.MovePlayerActivity;
import raulete.com.footballfield.s004moveonlongclick.MoveOnLongClickActivity;
import raulete.com.footballfield.s005aftermovedcallback.AfterMovedPlayerCallbackActivity;
import raulete.com.footballfield.s006exchangeplayer.ExchangePlayerActivity;
import raulete.com.footballfield.s007playerteam.PlayerTeamActivity;
import raulete.com.footballfield.s008teamcolor.TeamColorActivity;
import raulete.com.footballfield.s009movecallback.MovingCallbackActivity;
import raulete.com.footballfield.s010clickplayer.ClickPlayerActivity;
import raulete.com.footballfield.s012adplayercustomposition.CustomPositionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBasicField(View view) {
        openActivity(BasicFieldActivity.class);
    }

    public void openAddPlayer(View view) {
        openActivity(AddPlayerActivity.class);
    }

    public void openMovePlayer(View view) {
        openActivity(MovePlayerActivity.class);
    }

    public void openMovePlayerOnLongClick(View view) {
        openActivity(MoveOnLongClickActivity.class);
    }

    public void openAfterMovedCallback(View view) {
        openActivity(AfterMovedPlayerCallbackActivity.class);
    }

    public void openExchangePlayer(View view) {
        openActivity(ExchangePlayerActivity.class);
    }

    public void openPlayerTeam(View view) {
        openActivity(PlayerTeamActivity.class);
    }

    public void openTeamColor(View view) {
        openActivity(TeamColorActivity.class);
    }

    public void openMovingCallback(View view) {
        openActivity(MovingCallbackActivity.class);
    }

    public void openClickCallback(View view) {
        openActivity(ClickPlayerActivity.class);
    }

    public void openCustomPosition(View view) {
        openActivity(CustomPositionActivity.class);
    }

    private void openActivity(Class<?> cls){
        startActivity(new Intent(this, cls));
    }
}
