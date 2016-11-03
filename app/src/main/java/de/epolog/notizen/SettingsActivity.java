package de.epolog.notizen;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        int start = 8;
        int end = 20;
        int hours = (end - start) % 24;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, start);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long startHourMilli = cal.getTimeInMillis();

        cal.add(Calendar.HOUR_OF_DAY, hours);

        long endHourMilli = cal.getTimeInMillis();


        long currentMilli = System.currentTimeMillis();

        if (currentMilli >= startHourMilli && currentMilli < endHourMilli) {
            View someView = findViewById(R.id.activity_settings);
            View root = someView.getRootView();
            root.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        } else {
            View someView = findViewById(R.id.activity_settings);
            View root = someView.getRootView();
            root.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));
        }


    }




}
