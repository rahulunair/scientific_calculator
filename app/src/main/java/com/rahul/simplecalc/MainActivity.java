package com.rahul.simplecalc;

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


public class MainActivity extends ActionBarActivity {


    boolean calculated = false;
    Character op = ' ';
    String input_expr = "";
    int result, sum;
    EditText display;
    String memSave = "";

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
                else Toast.makeText(this, "more than one decimal specifier not allowed", Toast.LENGTH_SHORT).show();
               break;
       }

    }


    public void mem_click(View v){
        // memory functions

        switch (v.getId()) {
            case R.id.Btn_ms_id:
                Log.d("mem_click_save_before_c", input_expr);
                if (input_expr.matches("[.0-9]+")) {
                    memSave = input_expr;
                    Log.d("mem_click_save", memSave);
                }
                break;
            case R.id.Btn_mr_id:
                show(memSave);
                Log.d("mem_click_recall", memSave);
                break;
            case R.id.Btn_mc_id:
                memSave = "";
                Log.d("mem_click_clear", memSave);
                break;
            case R.id.Btn_m_plus_id:
                Log.d("mem_click_m_plus", memSave);
                //operate_mem("-", memSave);
                break;
            case R.id.Btn_m_minus_id:
                //operate_mem("+", memSave);
                Log.d("mem_click_m_minus", memSave);
                break;
        }
    }





    public void btn_plus_click(View v) {
        operation();
        calculated = false;
        op = '+';

    }

    public void btn_minus_click(View v) {
        operation();
        calculated = false;
        op = '-';

    }

    public void btn_div_click(View v) {
        operation();
        calculated = false;
        op = '/';

    }

    public void btn_mul_click(View v) {
        operation();
        calculated = false;
        op = '*';

    }

    public void btn_equal_click(View v) {

        if (calculated == false)
            calculate();
        else
            Toast.makeText(this, "Nothing to calculate..", Toast.LENGTH_SHORT).show();


    }

    public void btn_reset_click(View v) {
        reset();

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
            //result = Integer.parseInt(input_expr);
            display.setText(input_expr);

    }

    private void operation() {
        input_expr = "";
        sum = result;
    }

    private void calculate() {
        calculated = true;
        if (op == '+')
            result = sum + result;
        else if (op == '-')
            result = sum - result;
        else if (op == '/')
            result = sum / result;
        else if (op == '*')
            result = sum * result;
        display.setText("" + result);
    }

}



