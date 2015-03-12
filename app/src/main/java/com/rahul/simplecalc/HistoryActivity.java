package com.rahul.simplecalc;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class HistoryActivity extends ActionBarActivity {

    ArrayList <String> hist;
    TextView edit;
    //SharedPreferences saved_history;
    String hist_Stringified;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        hist = getIntent().getStringArrayListExtra("history");
        edit = (TextView)findViewById(R.id.text_input);
        edit.setTypeface(Typeface.SANS_SERIF);
        edit.setTextSize(15);
        edit.setTextColor(Color.BLUE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        hist_Stringified += hist.toString().replace("[", "").replace("]", "");
        if (hist_Stringified == "" || hist_Stringified == null)
            hist_Stringified = readFromFile();
       // hist_Stringified = saved_history.getString("history", null);
        edit.setText(hist_Stringified);

    }

    @Override
    protected void onPause() {
        super.onPause();
       // SharedPreferences.Editor editor = saved_history.edit();
        //editor.putString("history", TextUtils.join(",", hist));
        //editor.commit();
        writeToFile(hist_Stringified);

    }



    private void writeToFile(String data) {

        // persistant storage to a file history.txt
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("history.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    private String readFromFile() {
        // persistant storage read from a file history.txt

        String ret = "";

        try {
            InputStream inputStream = openFileInput("history.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }


}
