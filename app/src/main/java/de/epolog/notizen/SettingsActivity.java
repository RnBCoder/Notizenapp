package de.epolog.notizen;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import static de.epolog.notizen.R.id.dayModeHead;
import static de.epolog.notizen.R.id.dmBc;
import static de.epolog.notizen.R.id.dmTc;
import static de.epolog.notizen.R.id.dnMode;
import static de.epolog.notizen.R.id.endClock;
import static de.epolog.notizen.R.id.endHour;
import static de.epolog.notizen.R.id.nightModeHead;
import static de.epolog.notizen.R.id.nmBc;
import static de.epolog.notizen.R.id.nmTc;
import static de.epolog.notizen.R.id.startClock;
import static de.epolog.notizen.R.id.startHour;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }


    @Override
    protected void onStart() {
        super.onStart();
        getDelegate().onStart();


        int start = 20;
        int end = 8;

        this.dayNightMode(Calendar.getInstance(), start, end);
    }

    private void dayNightMode(Calendar cal, int startTime, int endTime){
        int now = cal.get(Calendar.HOUR_OF_DAY);
        if(now >= startTime || now < endTime)
            this.setColours(ContextCompat.getColor(this, R.color.grey), ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.hintgrey));
        else
            this.setColours(ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.black), ContextCompat.getColor(this, R.color.grey));
    }

    private void setColours(int bc, int tc, int hc){
        View someView = findViewById(R.id.activity_settings);
        View root = someView.getRootView();
        root.setBackgroundColor(bc);

        EditText editText1 = (EditText) findViewById(R.id.starHourValue);
        EditText editText2 = (EditText) findViewById(R.id.endHourValue);
        editText1.setTextColor(tc);
        editText2.setTextColor(tc);
        editText1.setHintTextColor(hc);
        editText2.setHintTextColor(hc);

        TextView text1 = (TextView) findViewById(dnMode);
        text1.setTextColor(tc);

        TextView text2 = (TextView) findViewById(startHour);
        text2.setTextColor(tc);

        TextView text3 = (TextView) findViewById(startClock);
        text3.setTextColor(tc);

        TextView text4 = (TextView) findViewById(endHour);
        text4.setTextColor(tc);

        TextView text5 = (TextView) findViewById(endClock);
        text5.setTextColor(tc);

        TextView text6 = (TextView) findViewById(dayModeHead);
        text6.setTextColor(tc);

        TextView text7 = (TextView) findViewById(dmBc);
        text7.setTextColor(tc);

        TextView text8 = (TextView) findViewById(dmTc);
        text8.setTextColor(tc);

        TextView text9 = (TextView) findViewById(nightModeHead);
        text9.setTextColor(tc);

        TextView text10 = (TextView) findViewById(nmBc);
        text10.setTextColor(tc);

        TextView text11 = (TextView) findViewById(nmTc);
        text11.setTextColor(tc);



    }



}






