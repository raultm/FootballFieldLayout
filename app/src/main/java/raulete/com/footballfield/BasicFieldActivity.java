package raulete.com.footballfield;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldPlayerCollection;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class BasicFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FieldPlayer fieldPlayer = new BaseFieldPlayer("Messi", "10");

        FieldPlayerCollection fieldPlayerCollection = new FieldPlayerCollection();
        fieldPlayerCollection.add(fieldPlayer);

        FootballFieldLayout footballFieldLayout = new FootballFieldLayout(this);
        footballFieldLayout.linkFieldPlayerCollection(fieldPlayerCollection);

        setContentView(footballFieldLayout);

    }
}
