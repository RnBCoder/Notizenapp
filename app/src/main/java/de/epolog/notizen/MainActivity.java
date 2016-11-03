package de.epolog.notizen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "Notiz.txt";
    private static final String DIR_NAME = "NotizenApp";

    private EditText editText;
    private File folder;
    private File savefile;



    public void getStartingText() {
        savefile = new File(folder, FILE_NAME);
        if (savefile.exists() && !savefile.isFile())
            errorOccured(); // in case file name is already used falsely
        else {
            if (!savefile.exists())
                try {
                    savefile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            else {
                try {

                    FileInputStream inputStream = new FileInputStream(savefile);

                    String completeTxt = "";
                    byte symbol;
                    while ((symbol = (byte) inputStream.read()) != -1) {
                        completeTxt += (char) symbol;
                    }

                    inputStream.close();
                    editText.setText (completeTxt);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Options:

                startActivity(new Intent(MainActivity.this, SettingsActivity.class));          //input settings class here

                return super.onOptionsItemSelected(item);

            case R.id.Info:

                AlertDialog alertDialog2 = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog2.setTitle("Info");
                alertDialog2.setMessage("App by Robert and Philipp");
                alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog2, int which) {
                      dialog2.dismiss();
        }
    });
                alertDialog2.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        folder = new File(getCacheDir(), DIR_NAME);

        if (!folder.exists())
            folder.mkdirs();
        else if(!folder.isDirectory())
            errorOccured(); // in case directory name is used falsely already




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



        long currentMilli= System.currentTimeMillis();

        if(currentMilli >= startHourMilli && currentMilli < endHourMilli){
                View someView = findViewById(R.id.activity_main);
                View root = someView.getRootView();
                root.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                editText.setTextColor(ContextCompat.getColor(this, R.color.black));
            } else{
                View someView = findViewById(R.id.activity_main);
                View root = someView.getRootView();
                root.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));
                editText.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
    }



    @Override
    protected void onStart() {
        super.onStart();
        getDelegate().onStart();

        getStartingText();

    }



    private void errorOccured(){
        Toast.makeText(getApplicationContext(), "Error in your filesystem, please contact the developer :)", Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onPause(){
        super.onPause();
        try {
            OutputStream outputStream = new FileOutputStream(savefile);
            String toWrite = (editText.getText() != null) ? editText.getText().toString() : "";
            outputStream.write(toWrite.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}