package raulete.com.footballfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import raulete.com.footballfield.s001emptyfield.BasicFieldActivity;
import raulete.com.footballfield.s002addplayer.AddPlayerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBasicField(View view) {
        Intent openBasicFieldIntent = new Intent(this, BasicFieldActivity.class);
        startActivity(openBasicFieldIntent);
    }

    public void openAddPlayer(View view) {
        Intent openIntent = new Intent(this, AddPlayerActivity.class);
        startActivity(openIntent);
    }
}
