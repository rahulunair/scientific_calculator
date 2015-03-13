package com.rahul.simplecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import mathparser.Parser;
import mathparser.ParsingException;


public class MainActivity extends ActionBarActivity {

    SharedPreferences save_memory_variables;
    ArrayList<String> history = new ArrayList<>();
    Character op = ' ';
    String input_expr = "";
    int result, sum;
    EditText display;
    String memSave = "";
    double solution = 0.0;
    String expr;
    boolean alternate = false;
    public static final String TAG = "main_act";

    boolean dotFlag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);   // relative layout
        display = (EditText) findViewById(R.id.result_id);
        history.add("");


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
               // if (dotFlag == false) {
                    show(".");
                 //   dotFlag = true;
                //} else
                  //  Toast.makeText(this, "more than one decimal specifier, not allowed", Toast.LENGTH_SHORT).show();
                break;
        }

    }


    public void mem_click(View v) {
        // memory functions

        switch (v.getId()) {
            case R.id.Btn_ms_id:
                Log.d(TAG, input_expr);
                if (input_expr.matches("[.0-9]+")) {
                    saveHistory("MS");
                    memSave = input_expr;
                    Log.d(TAG, memSave);
                }
                break;
            case R.id.Btn_mr_id:
                saveHistory("MR");
                show(memSave);
                Log.d(TAG, memSave);
                break;
            case R.id.Btn_mc_id:
                saveHistory("MC: ");
                memSave = "";
                Log.d(TAG, memSave);
                break;
            case R.id.Btn_m_plus_id:
                Log.d(TAG, memSave);
                saveHistory("M+: ");
                if(memSave!= "") show("+"+memSave);
                break;
            case R.id.Btn_m_minus_id:
                if(memSave!= "") show("+"+memSave);
                Log.d(TAG, memSave);
                saveHistory("M+: ");
                break;
        }
    }

    public void parseMath() {
        // parser to calculate math
        Parser parser = new Parser();
        String[] operands;
        String oper1;
        String oper2;
        try {
            if (input_expr.contains("√")) {
                operands = input_expr.split("√");
                if (operands[0] != "" || operands[0] != null) {
                    oper1 = operands[0].split("[^\\w\\.]+")[operands[0].split("[^\\w\\.]+").length - 1]; // the last element of the array
                    oper2 = operands[1].split("[^\\w\\.]+")[0]; // The first element of the the  array of non special characters
                    input_expr = input_expr.substring(0, input_expr.lastIndexOf(oper1));
                    input_expr += "power(" + oper2 + ",1/" + oper1 + ")";
                }
            }
                if (input_expr.contains("P")) {
                    operands = input_expr.split("P");
                    if (operands[0] != "" || operands[0] != null) {
                        oper1 = operands[0].split("[^\\w\\.]+")[operands[0].split("[^\\w\\.]+").length - 1]; // the last element of the array
                        oper2 = operands[1].split("[^\\w\\.]+")[0]; // The first element of the the  array of non special characters
                        input_expr = input_expr.substring(0, input_expr.lastIndexOf(oper1));
                        input_expr += "power(" + oper1+ ","+oper2 + ")";
                    }

            }

            Log.d("input_expr", input_expr);
            parser.parse(input_expr);
            solution = parser.getNumericAnswer();
            reset();
            String result;
            result = Double.toString(solution);
            input_expr = input_expr + result;
            Log.d(TAG, result);
            saveHistory(input_expr+"\n");
            display.setText(input_expr);

        } catch (ParsingException ex) {
            Toast.makeText(this, "Illegal operation", Toast.LENGTH_SHORT).show();
            reset();
            Log.e("Error in Parsing", ex.toString());
        }
    }

    public void operation_click(View v) {
        // all math operations
        String local = input_expr;
        String[] ops = input_expr.split("[^\\w\\.]+");
        Log.d("split_expr", Arrays.toString(ops));
        switch (v.getId()) {
            case R.id.Btn_equal_id:
                saveHistory("=");
                parseMath();
                break;
            case R.id.Btn_c_id:
                reset();
                saveHistory("C");
                break;
            case R.id.Btn_ce_id:
                if (ops.length != 0) {
                    input_expr = input_expr.replace(ops[ops.length - 1], "");
                    show("");
                } else reset();
                break;
            case R.id.Btn_left_id:
                if (input_expr.length() > 0) {
                    input_expr = input_expr.substring(0, input_expr.length() - 1);
                    show("");
                } else
                    saveHistory("CE");
                    reset();
                break;
            case R.id.Btn_plus_id:
                show("+");
                break;
            case R.id.Btn_minus_id:
                show("-");
                break;
            case R.id.Btn_mul_id:
                show("*");
                break;
            case R.id.Btn_div_id:
                show("/");
                break;
            case R.id.Btn_perc_id:
                show("%");
                break;
            case R.id.button_x_2:
                reset();
                show("power(" + local + ", 2)");
                break;
            case R.id.Btn_sq_id:
                reset();
                show("sqrt(" + local + ")");
                break;
            case R.id.button_x_3:
                reset();
                show("power(" + local + ", 3)");
                break;
            case R.id.Btn_plus_minus_id:
                reset();
                double l = Double.parseDouble(local);
                l = 0 - l;
                show(Double.toString(l));
                break;
            case R.id.button_3_root_x:
                reset();
                show("power(" + local + ", 1/3)");
                break;
            case R.id.button_y_root_x:
                show("√");
                break;
            case R.id.Btn_one_by_x_id:
                reset();
                show("1/" + local);
                break;
            case R.id.Btn_l_b:
                show("(");
                break;
            case R.id.Btn_r_b:
                show(")");
                break;
            case R.id.Btn_log_id:
                reset();
                show("log10(" + local + ")");
                break;
            case R.id.Btn_10_x_id:
                reset();
                show("power(10," + local + ")");
                break;
            case R.id.Btn_pi_id:
                show("pi");
                break;
            case R.id.button_n_fact:
                reset();
                show("factorial(" + local + ")");
                break;
            case R.id.button_x_y:
                show("P");
                break;
            case R.id.Btn_mod_id:
                show("%");


        }
    }

    private void saveHistory(String save) {
        history.add(save);
        Log.d("history", history.toString());
    }

    private void reset() {
        input_expr = "";
        display.setText("");
    }


    private void show(String value) {
        input_expr = input_expr + value;
        Log.d(TAG, value);
        saveHistory(value);
        display.setText(input_expr);
    }


    public void show_history(View view) {
        System.out.println("entered show history func");
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        intent.putExtra("history", history);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        save_memory_variables = getSharedPreferences("memSave_tag", 0);
        super.onPause();
        SharedPreferences.Editor editor = save_memory_variables.edit();
        editor.putString("memSave_tag", memSave);
        editor.commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        memSave = save_memory_variables.getString("memSave_tag", null);
    }
}



