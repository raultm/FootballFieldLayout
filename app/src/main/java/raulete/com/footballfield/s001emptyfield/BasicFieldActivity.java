package raulete.com.footballfield.s001emptyfield;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import raulete.com.footballfield.R;
import raulete.com.footballfield.custom.FieldPlayer;
import raulete.com.footballfield.custom.FieldPlayerCollection;
import raulete.com.footballfield.custom.FootballFieldLayout;

public class BasicFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic);

        FootballFieldLayout footballFieldLayout = (FootballFieldLayout) findViewById(R.id.activity_basic);

        FieldPlayer fieldPlayer = new BaseFieldPlayer("Messi", "10");

        FieldPlayerCollection fieldPlayerCollection = new FieldPlayerCollection();
        fieldPlayerCollection.add(fieldPlayer);

        footballFieldLayout.linkFieldPlayerCollection(fieldPlayerCollection);

        setContentView(footballFieldLayout);
    }
}
