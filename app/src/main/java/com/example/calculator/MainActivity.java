package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button []btnNum = new Button[10];
    HashMap <String, Button> btnOperation;
    Button divide, multiply, substract, add, enter, decimal, clear;
    Button remove;
    TextView screen, result;
    ArrayList <String> operand;
    ArrayList <String> operation;
    HashMap <String, Integer> degree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView) findViewById(R.id.screen);
        result = (TextView) findViewById(R.id.result);
        btnNum[0] = (Button) findViewById(R.id.btn_0);
        btnNum[1] = (Button) findViewById(R.id.btn_1);
        btnNum[2] = (Button) findViewById(R.id.btn_2);
        btnNum[3] = (Button) findViewById(R.id.btn_3);
        btnNum[4] = (Button) findViewById(R.id.btn_4);
        btnNum[5] = (Button) findViewById(R.id.btn_5);
        btnNum[6] = (Button) findViewById(R.id.btn_6);
        btnNum[7] = (Button) findViewById(R.id.btn_7);
        btnNum[8] = (Button) findViewById(R.id.btn_8);
        btnNum[9] = (Button) findViewById(R.id.btn_9);
        clear = (Button) findViewById(R.id.btn_clear);
        divide = (Button) findViewById(R.id.btn_divide);
        multiply = (Button) findViewById(R.id.btn_multiply);
        substract = (Button) findViewById(R.id.btn_substract);
        add = (Button) findViewById(R.id.btn_add);
        enter = (Button) findViewById(R.id.btn_enter);
        decimal = (Button) findViewById(R.id.btn_decimal);
        remove = (Button) findViewById(R.id.remove);
        operand = new ArrayList<String>();
        operation = new ArrayList<String>();
        degree = new HashMap<String, Integer>();
        btnOperation = new HashMap<String, Button>();
        degree.put("+", 1);
        degree.put("-", 1);
        degree.put("*", 2);
        degree.put("/", 2);
        btnOperation.put("+", add);
        btnOperation.put("-", substract);
        btnOperation.put("*", multiply);
        btnOperation.put("/", divide);
        Operation.setEnable(false, clear, enter, substract, multiply, add, divide, decimal, remove);
        for (int i = 0; i < 10; i++) {
            btnNum[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    screen.setText(screen.getText().toString() + ((Button) view).getText().toString());
                    result.setText("");
                    Operation.setEnable(true, clear, enter, substract, multiply, add, divide, decimal, remove);
                }
            });
        }
        for (Map.Entry<String, Button> btn_operation:btnOperation.entrySet()) {
            String key = btn_operation.getKey();
            Button btn = btnOperation.get(key);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    screen.setText(screen.getText().toString() + key);
                    result.setText("");
                    Operation.setEnable(false, enter, substract, multiply, add, divide, decimal);
                }
            });
        }
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText("");
                result.setText("");
                Operation.setEnable(false, clear, enter, substract, multiply, add, divide, decimal, remove);
                operand.clear();
                operation.clear();
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(screen.getText().toString());
                String op = "";
                for (int i = 0, length = s.length(); i < length; i++) {
                    if(Character.isDigit(s.charAt(i)) || s.charAt(i) == '.'){
                        op += String.valueOf(s.charAt(i));
                    }
                    else{
                        operand.add(op);
                        op = "";
                        operation.add(String.valueOf(s.charAt(i)));
                    }
                }
                operand.add(op);
                for (int i = 0; i < operation.size() ; i++) {
                    if(i == operation.size() - 1){
                        operand.set(i, Operation.calculate(operand.get(i), operation.get(i), operand.get(i + 1)));
                        operand.remove(i + 1);
                        operation.remove(i);
                        i = -1;
                    }
                    else if(degree.get(operation.get(i)) >= degree.get(operation.get(i + 1))){
                        operand.set(i, Operation.calculate(operand.get(i), operation.get(i), operand.get(i + 1)));
                        operand.remove(i + 1);
                        operation.remove(i);
                        i = -1;
                    }
                }
                result.setText(operand.get(0));
                operand.clear();
                operation.clear();
            }
        });
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText().toString() + ((Button) view).getText().toString());
                result.setText("");
                Operation.setEnable(false, enter, substract, multiply, add, divide, decimal);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer s = new StringBuffer(screen.getText().toString());
                s.deleteCharAt(s.length() - 1);
                screen.setText(s.toString());
                result.setText("");
                if(screen.getText().equals("")){
                    Operation.setEnable(false, clear, enter, substract, multiply, add, divide, decimal, remove);
                    operand.clear();
                    operation.clear();
                }
                else if(Character.isDigit(s.charAt(s.length() - 1))){
                    Operation.setEnable(true, clear, enter, substract, multiply, add, divide, decimal, remove);
                }
                else if(s.charAt(s.length() - 1) == '.'){
                    Operation.setEnable(false, enter, substract, multiply, add, divide, decimal);
                    Operation.setEnable(true, remove, clear);
                }
                else{
                    Operation.setEnable(false, enter, substract, multiply, add, divide, decimal);
                    Operation.setEnable(true, remove, clear);
                }
            }
        });
    }
}