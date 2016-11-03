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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "Notiz.txt";
    private static final String DIR_NAME = "NotizenApp";

    private EditText editText;
    private File folder;
    private File saveFile;



    public void getStartingText() {
        saveFile = new File(folder, FILE_NAME);
        if (saveFile.exists() && !saveFile.isFile())
            errorOccured(); // in case file name is already used falsely
        else {
            if (!saveFile.exists())
                try {
                    saveFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            else {
                try {

                    BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(saveFile), "UTF-8"));

                    String completeTxt = "";
                    String line = inputStream.readLine();
                    while (line != null) {
                        completeTxt += line + "\n";
                        line = inputStream.readLine();
                    }

                    inputStream.close();
                    editText.setText(completeTxt);

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDelegate().onStart();

        getStartingText();

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
        View someView = findViewById(R.id.activity_main);
        View root = someView.getRootView();
        root.setBackgroundColor(bc);
        editText.setTextColor(tc);
    }

    private void errorOccured(){
        Toast.makeText(getApplicationContext(), "Error in your filesystem, please contact the developer :)", Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onPause(){
        super.onPause();
        try {
            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(saveFile), "UTF-8");
            String toWrite = (editText.getText() != null) ? editText.getText().toString() : "";
            outputStream.write(toWrite, 0, toWrite.length());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}