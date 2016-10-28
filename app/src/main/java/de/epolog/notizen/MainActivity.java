package de.epolog.notizen;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        folder = new File(getCacheDir(), DIR_NAME);

        if (!folder.exists())
            folder.mkdirs();
        else if(!folder.isDirectory())
            errorOccured(); // in case directory name is used falsely already



        View someView = findViewById(R.id.activity_main);
        View root = someView.getRootView();
        root.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        editText.setTextColor(ContextCompat.getColor(this, R.color.black));

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