package de.epolog.notizen;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;

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
            this.setColours(ContextCompat.getColor(this, R.color.grey), ContextCompat.getColor(this, R.color.white));
        else
            this.setColours(ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.black));
    }

    private void setColours(int bc, int tc){
        View someView = findViewById(R.id.activity_settings);
        View root = someView.getRootView();
        root.setBackgroundColor(bc);
    }



}






