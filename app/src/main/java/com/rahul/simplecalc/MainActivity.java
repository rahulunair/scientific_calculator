package com.rahul.simplecalc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mathparser.Parser;
import mathparser.ParsingException;


public class MainActivity extends ActionBarActivity {


    ArrayList <String> history = new ArrayList<>();
    Character op = ' ';
    String input_expr = "";
    int result, sum;
    EditText display;
    String memSave = "";
    double solution = 0.0;

    boolean dotFlag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);   // relative layout
        display = (EditText) findViewById(R.id.result_id);


    }


    public void number_click(View v) {
        // Displays clicked numbers

       switch (v.getId()) {
           case R.id.Btn_0_id:
               show("0");
               break;
           case R.id.Btn_1_id:
               show("1");
               break;
           case R.id.Btn_2_id:
               show("2");
               break;
           case R.id.Btn_3_id:
               show("3");
               break;
           case R.id.Btn_4_id:
               show("4");
               break;
           case R.id.Btn_5_id:
               show("5");
               break;
           case R.id.Btn_6_id:
               show("6");
               break;
           case R.id.Btn_7_id:
               show("7");
               break;
           case R.id.Btn_8_id:
               show("8");
               break;
           case R.id.Btn_9_id:
               show("9");
               break;
           case R.id.Btn_dot_id:
               if(dotFlag == false) {
                   show(".");
                   dotFlag =true;
               }
                else Toast.makeText(this, "more than one decimal specifier, not allowed", Toast.LENGTH_SHORT).show();
               break;
       }

    }


    public void mem_click(View v){
        // memory functions

        switch (v.getId()) {
            case R.id.Btn_ms_id:
                Log.d("mem_click_save_before_c", input_expr);
                if (input_expr.matches("[.0-9]+")) {
                    saveHistory("MS");
                    memSave = input_expr;
                    Log.d("mem_click_save", memSave);
                }
                break;
            case R.id.Btn_mr_id:
                saveHistory("MR");
                show(memSave);
                Log.d("mem_click_recall", memSave);
                break;
            case R.id.Btn_mc_id:
                saveHistory("MC: ");
                memSave = "";
                Log.d("mem_click_clear", memSave);
                break;
            case R.id.Btn_m_plus_id:
                Log.d("mem_click_m_plus", memSave);
                saveHistory("M+: ");
                //operate_mem("-", memSave);
                break;
            case R.id.Btn_m_minus_id:
                //operate_mem("+", memSave);
                Log.d("mem_click_m_minus", memSave);
                saveHistory("M-: ");
                break;
        }
    }


    public void parseMath (){
        // parser to calculate math

        Parser parser = new Parser();

        try {
            parser.parse(input_expr);
            solution = parser.getNumericAnswer();
            reset();
            show(Double.toString(solution));
        } catch(ParsingException ex) {
            Toast.makeText(this, "Illegal operation", Toast.LENGTH_SHORT).show();
            reset();
            Log.e("Error in Parsing", ex.toString());
        }
    }

    public void operation_click(View v){
        // all math operations

        switch (v.getId()){
            case R.id.Btn_equal_id:
                saveHistory("=");
                parseMath();
                break;
            case R.id.Btn_plus_id:
                show("+");
                break;
        }
    }

    private void saveHistory (String save){
        history.add(save);
        Log.d("history", history.toString());
    }

    private void reset() {
        input_expr = "";
        op = ' ';
        result = 0;
        sum = 0;
        display.setText("");
    }


    private void show(String value) {
            input_expr = input_expr + value;
            Log.d("show fn", value);
            saveHistory(value);
            //result = Integer.parseInt(input_expr);
            display.setText(input_expr);
    }



    public void show_history(View view)
    {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        intent.putExtra("history", history);
        startActivity(intent);

    }

}



