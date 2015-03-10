package com.rahul.simplecalc;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class HistoryActivity extends ActionBarActivity {

    ArrayList <String> hist = getIntent().getStringArrayListExtra("history");
    TextView edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
       edit = (TextView)findViewById(R.id.text_input);


    }

    @Override
    protected void onStart() {
        super.onStart();
        edit.setText(hist.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();


        FileOutputStream fos = getContext().openFileOutput("calc_history", Context.MODE_PRIVATE);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(returnlist);
            oos.close();
        } catch (Exception e) {
            Log.e("error in writing", e.toString())
        }

    }

}
