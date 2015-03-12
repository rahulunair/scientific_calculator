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
import java.io.FileOutputStream;
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
        hist_Stringified = readFromFile();
        hist_Stringified += hist.toString().replace("[", "").replace("]", "");

       // hist_Stringified = saved_history.getString("history", null);
        if (hist_Stringified == null) hist_Stringified = " ";
        Log.d("hist_Stringified", hist_Stringified);
        edit.setText(hist_Stringified);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // SharedPreferences.Editor editor = saved_history.edit();
        //editor.putString("history", TextUtils.join(",", hist));
        //editor.commit();
        Log.d("hist_Stringified",hist_Stringified );
        writeToFile(hist_Stringified);

    }



    private void writeToFile(String data) {

        // persistant storage to a file history.txt
        try {
            FileOutputStream fos = openFileOutput("data_save_00", Context.MODE_PRIVATE | Context.MODE_WORLD_READABLE);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (IOException e) {
            Log.e("history_activity", "File write failed: " + e.toString());
        }
    }


    private String readFromFile() {
        // persistant storage read from a file history.txt
        String ret = "";

        try {

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput("data_save_00")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
            ret = stringBuffer.toString();
        } catch (IOException io){io.printStackTrace();}

    return ret;
    }


}
