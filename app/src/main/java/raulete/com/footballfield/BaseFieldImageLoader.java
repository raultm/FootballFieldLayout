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
        if(url.equals("EUD")){
            fieldTeamView.setImageResource(R.mipmap.eud);
        }else if(url.equals("FCB")){
            fieldTeamView.setImageResource(R.mipmap.fcb);
        }else if(url.equals("RMD")){
            fieldTeamView.setImageResource(R.mipmap.rmd);
        }else{
            fieldTeamView.setBackgroundColor(Color.parseColor("#66666666"));
        }
    }
}
