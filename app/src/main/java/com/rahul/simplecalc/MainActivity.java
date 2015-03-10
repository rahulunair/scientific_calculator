package com.rahul.simplecalc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public String display_this = "";
    boolean calculated = false;
    Character op = ' ';
    int result, sum;
    EditText display;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);   // relative layout
        display = (EditText) findViewById(R.id.result_id);


    }










    public void btn_0_click(View v) {
        show("0");
    }


    public void btn_1_click(View v) {
        show("1");
    }

    public void btn_2_click(View v) {
        show("2");

    }

    public void btn_3_click(View v) {
        show("3");

    }

    public void btn_4_click(View v) {
        show("4");

    }

    public void btn_5_click(View v) {
        show("5");

    }

    public void btn_6_click(View v) {
        show("6");

    }

    public void btn_7_click(View v) {
        show("7");

    }

    public void btn_8_click(View v) {
        show("8");

    }

    public void btn_9_click(View v) {
        show("9");

    }

    public void btn_dot_click(View v) {
        //show(".");
        Toast.makeText(this, "Not implemented, resetting..", Toast.LENGTH_LONG).show();

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
        display_this = "";
        op = ' ';
        result = 0;
        sum = 0;
        display.setText("");
    }


    private void show(String value) {
        display_this = display_this + value;
        result = Integer.parseInt(display_this);
        display.setText(display_this);
    }

    private void operation() {
        display_this = "";
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



