package raulete.com.footballfield;

import android.graphics.Color;

import raulete.com.footballfieldlayout.FieldImageLoader;
import raulete.com.footballfieldlayout.FieldTeamView;


/**
 * Created by raulete on 5/12/16.
 */

public class BaseFieldImageLoader implements FieldImageLoader {

    @Override
    public void load(FieldTeamView fieldTeamView, String url) {
        if(fieldTeamView.getFieldTeam().getShortName().equals("EUD")){
            fieldTeamView.setImageResource(R.mipmap.eud);
        }else{
            fieldTeamView.setBackgroundColor(Color.parseColor("#66666666"));
        }
    }
}
